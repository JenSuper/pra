package com.jensuper.prc.excel.saxmodel;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/06
 */
public class BigExcelParse extends DefaultHandler {
    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    /**
     * Table with styles
     */
    private StylesTable stylesTable;

    //取SST 的索引对应的值
    private SharedStringsTable sharedStringsTable;
    /**
     * 最大列数
     */
    private final int minColumnCount;
    private int minColumnCountFind;

    //单元格内容是SST 的索引
    private boolean vIsOpen;

    private xssfDataType nextDataType;

    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;

    private int thisColumn = -1;
    // The last column printed to the output stream
    private int lastColumnNumber = -1;

    // Gathers characters as they are seen.
    private StringBuffer value;
    private String[] record;//封装每一行的结果
    private List<List<String>> rows = new ArrayList<>();//封装结果
    private boolean isCellNull = false;
    private SimpleDateFormat sdf = null;
    private static DecimalFormat df = new DecimalFormat("###########");


    //构造器
    public BigExcelParse(StylesTable styles,
                         SharedStringsTable strings, int cols) {
        this.stylesTable = styles;
        this.sharedStringsTable = strings;
        this.minColumnCount = cols;
        this.value = new StringBuffer();
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = new DataFormatter();
        record = new String[this.minColumnCount];
        rows.clear();// 每次读取都清空行集合
    }

    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {

        if ("inlineStr".equals(name) || "v".equals(name)) {
            vIsOpen = true;
            // Clear contents cache
            value.setLength(0);
        }
        // c => cell
        else if ("c".equals(name)) {
            // Get the cell reference
            String r = attributes.getValue("r");
            int firstDigit = -1;
            for (int c = 0; c < r.length(); ++c) {
                if (Character.isDigit(r.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            thisColumn = nameToColumn(r.substring(0, firstDigit));

            // Set up defaults.
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = -1;
            this.formatString = null;
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType))
                nextDataType = xssfDataType.BOOL;
            else if ("e".equals(cellType))
                nextDataType = xssfDataType.ERROR;
            else if ("inlineStr".equals(cellType))
                nextDataType = xssfDataType.INLINESTR;
            else if ("s".equals(cellType))
                nextDataType = xssfDataType.SSTINDEX;
            else if ("str".equals(cellType))
                nextDataType = xssfDataType.FORMULA;
            else if (cellStyleStr != null) {
                // It's a number, but almost certainly one
                // with a special style or format
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null)
                    this.formatString = BuiltinFormats
                            .getBuiltinFormat(this.formatIndex);
            }
        }

    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {

        String thisStr = null;

        // v => contents of a cell
        //单元格内容标签结束，characters方法会被调用处理内容
        if ("v".equals(name)) {
            // Process the value contents as required.
            // Do now, as characters() may be called more than once
            switch (nextDataType) {

                case BOOL:
                    char first = value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;

                case ERROR:
                    thisStr = "\"ERROR:" + value.toString() + '"';
                    break;

                case FORMULA:
                    // A formula could result in a string value,
                    // so always add double-quote characters.
                    thisStr = value.toString();
                    break;

                case INLINESTR:
                    // TODO: have seen an example of this, so it's untested.
                    XSSFRichTextString rtsi = new XSSFRichTextString(
                            value.toString());
                    thisStr = rtsi.toString();
                    break;

                case SSTINDEX:
                    String sstIndex = value.toString();
                    try {
                        int idx = Integer.parseInt(sstIndex);
                        XSSFRichTextString rtss = new XSSFRichTextString(
                                sharedStringsTable.getEntryAt(idx));
                        thisStr = rtss.toString();
                    } catch (NumberFormatException ex) {
                        System.out.println("Failed to parse SST index '" + sstIndex
                                + "': " + ex.toString());
                    }
                    break;

                case NUMBER:
                    String n = value.toString();
                    // 判断是否是日期格式
                    if (formatIndex == 14 || formatIndex == 22|| formatIndex == 31 || formatIndex == 57 || formatIndex == 58
                            || (176 <= formatIndex && formatIndex <= 181) || (182 <= formatIndex && formatIndex <= 196)
                            || (210 <= formatIndex && formatIndex <= 213) || (208 == formatIndex)) {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(Double.parseDouble(n));
                        thisStr = sdf.format(date);
                    } else if (formatIndex == 20 || formatIndex == 32 || formatIndex == 183 || (200 <= formatIndex && formatIndex <= 209)) {//时间
                        sdf = new SimpleDateFormat("HH:mm:ss");
                        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(Double.parseDouble(n));
                        thisStr = sdf.format(date);
                    } else {
                        if (n.contains("E")) {//科学计数法
                            BigDecimal decimal = new BigDecimal(n);
                            BigDecimal bigDecimal = decimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                            thisStr = ObjectUtils.identityToString(bigDecimal);
//                            DecimalFormat df = new DecimalFormat("0");
//                            String format = df.format(value.toString());
//                            String[] split = n.split("\\+");
//                            String e = split[0].replaceAll("E|e", "");
//                            thisStr = e.replace(".", "");
                        } else {
                            thisStr = n;
                        }
                    }
                    break;
                default:
                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
                    break;
            }
            if (lastColumnNumber == -1) {
                lastColumnNumber = 0;
            }
            //判断单元格的值是否为空
            if (thisStr == null || "".equals(isCellNull)) {
                isCellNull = true;// 设置单元格是否为空值
            }
            record[thisColumn] = thisStr;
            // Update column
            if (thisColumn > -1)
                lastColumnNumber = thisColumn;
            //行结束,存储一行数据
        } else if ("row".equals(name)) {
            // Print out any missing commas if needed
            if (minColumnCount > 0) {
                // Columns are 0 based
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                // 判断是否空行
                if (record != null && record.length != 0) {
                    //确定列数
                    findColIndex();
                    String[] clone = record.clone();
                    if (rows.size() > 0) {
                        List<String> listOld = new ArrayList<String>(Arrays.asList(clone));
                        List<String> listNew = new ArrayList<>();
                        for (int i = 0; i < listOld.size(); i++) {
                            if (minColumnCountFind == i) {
                                break;
                            }
                            listNew.add(listOld.get(i));
                        }
//                        rows.remove(rows.size() - 1);
                        rows.add(listNew);
                    } else {
                        List<String> listOld = new ArrayList<String>(Arrays.asList(clone));
                        rows.add(listOld);
                    }
                    isCellNull = false;
                    for (int i = 0; i < record.length; i++) {
                        record[i] = null;
                    }
                }
            }
            lastColumnNumber = -1;
        }

    }

    /**
     * 取出第一行数据，确定表头列的索引，用来确定数据列结束位置
     *
     * @param
     * @return void
     * @author jichao
     * @date 2019/09/06 17:17
     */
    public void findColIndex() {
        if (rows.size() != 1) {
            return;
        }
        for (int i = 0; i < rows.size(); i++) {

            List<String> strList = rows.get(i);
            List<String> listNew = new ArrayList<>();

            for (int j = 0; j < strList.size(); j++) {
                String s = strList.get(j);

                if (StringUtils.isNotEmpty(s)) {
                    listNew.add(s);
                } else {
                    rows.remove(0);
                    rows.add(listNew);
                    minColumnCountFind = j;
                    return;
                }
            }
        }
    }

//    public List<String[]> getRows() {
    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (vIsOpen)
            value.append(ch, start, length);
    }

    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }
}

