package com.jensuper.prc.common.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.util.ObjectUtils;
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
 * @description: POI 使用事件模式读取excel
 * @date 2019/09/06
 */
public class ExcelXlsxReader extends DefaultHandler {
    private static DecimalFormat df = new DecimalFormat("###########");
    /**
     * 单元格类型
     */
    private static String inlineStr = "inlineStr";
    private static String row = "row";
    private static String v = "v";
    private static String c = "c";
    private static String b = "b";
    private static String e = "e";
    private static String s = "s";
    private static String str = "str";
    private static String E = "E";
    /**
     * 日期索引类型
     */
    private static int oneFour = 14;
    private static int threeOne = 31;
    private static int fiveSeven = 57;
    private static int fiveEight = 58;
    private static int oneSevenSix = 176;
    private static int oneSevenEight = 178;
    private static int oneSevenNine = 179;
    private static int oneEightOne = 181;
    private static int oneEightTwo = 182;
    private static int oneNineSix = 196;
    private static int twoZeroEight = 208;
    private static int twoOneZero = 210;
    private static int twoOneThree = 213;
    private static int TwoZero = 20;
    private static int TwoOne = 21;
    private static int TwoTwo = 22;
    private static int ThreeTwo = 32;
    private static int oneEightThree = 183;
    private static int twoZeroZero = 200;
    private static int twoZeroNine = 209;
    /**
     * 最大列数
     */
    private final int minColumnCount;
    private final DataFormatter formatter;
    /**
     * Table with styles
     */
    private StylesTable stylesTable;
    /**
     * 取SST 的索引对应的值
     */
    private SharedStringsTable sharedStringsTable;
    private int minColumnCountFind;
    /**
     * 单元格内容是SST 的索引
     */
    private boolean vIsOpen;
    private xssfDataType nextDataType;
    private short formatIndex;
    private String formatString;
    private int thisColumn = -1;
    /**
     * The last column printed to the output stream
     */
    private int lastColumnNumber = -1;
    private StringBuffer value;
    /**
     * 封装每一行的结果
     */
    private String[] record;
    /**
     * 封装结果
     */
    private List<List<String>> rows = new ArrayList<>();
    private boolean isCellNull = false;
    private SimpleDateFormat sdf = null;
    /**
     * 构造器
     *
     * @param styles
     * @param strings
     * @param cols
     */
    public ExcelXlsxReader(StylesTable styles,
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

    /**
     * 开始元素
     *
     * @param uri
     * @param localName
     * @param name
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {

        if (inlineStr.equals(name) || v.equals(name)) {
            vIsOpen = true;
            // Clear contents cache
            value.setLength(0);
        }
        // c => cell
        else if (c.equals(name)) {
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
            if (b.equals(cellType)) {
                nextDataType = xssfDataType.BOOL;
            } else if (e.equals(cellType)) {
                nextDataType = xssfDataType.ERROR;
            } else if (inlineStr.equals(cellType)) {
                nextDataType = xssfDataType.INLINESTR;
            } else if (s.equals(cellType)) {
                nextDataType = xssfDataType.SSTINDEX;
            } else if (str.equals(cellType)) {
                nextDataType = xssfDataType.FORMULA;
            } else if (cellStyleStr != null) {
                // It's a number, but almost certainly one
                // with a special style or format
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null) {
                    this.formatString = BuiltinFormats
                            .getBuiltinFormat(this.formatIndex);
                }
            }
        }

    }

    /**
     * 结束元素
     *
     * @param uri
     * @param localName
     * @param name
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {

        String thisStr = null;

        // v => contents of a cell
        //单元格内容标签结束，characters方法会被调用处理内容
        if (v.equals(name)) {
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
                    // have seen an example of this, so it's untested.
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
//                        Log.info("Failed to parse SST index '" + sstIndex
//                                + "': " + ex.toString());
                    }
                    break;

                case NUMBER:
                    String n = value.toString();
                    // 判断是否是日期格式
                    Boolean checkDateFlag = checkDate(formatIndex);
                    // 判断是否是时间格式
                    Boolean checkTimeFlag = checkTime(formatIndex);
                    if (checkDateFlag) {
                        sdf = new SimpleDateFormat(TextDataUtil.datePattern);
                        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(Double.parseDouble(n));
                        thisStr = sdf.format(date);
                    } else if (checkTimeFlag) {
                        sdf = new SimpleDateFormat("HH:mm:ss");
                        Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(Double.parseDouble(n));
                        thisStr = sdf.format(date);
                    } else {
                        // 科学计数法
                        if (n.contains(E)) {
                            BigDecimal decimal = new BigDecimal(n);
                            BigDecimal bigDecimal = decimal.setScale(4, BigDecimal.ROUND_HALF_UP);
                            thisStr = ObjectUtils.nullSafeToString(bigDecimal);
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
                // 设置单元格是否为空值
                isCellNull = true;
            }
            record[thisColumn] = thisStr;
            // Update column
            if (thisColumn > -1) {
                lastColumnNumber = thisColumn;
            }
            //行结束,存储一行数据
        } else if (row.equals(name)) {
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
                        rows.add(listNew);
                    } else {
                        List<String> listOld = new ArrayList<>(Arrays.asList(clone));
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
     * 验证是日期格式
     *
     * @param
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/09 11:45
     */
    private Boolean checkDate(int formatIndex) {
        if (formatIndex == oneFour) {
            return true;
        }
        if (formatIndex == threeOne) {
            return true;
        }
        if (formatIndex == fiveSeven) {
            return true;
        }
        if (formatIndex == fiveEight) {
            return true;
        }
        if (formatIndex == TwoTwo) {
            return true;
        }
        if (oneSevenSix <= formatIndex && formatIndex <= oneEightOne) {
            return true;
        }
        if (oneEightTwo <= formatIndex && formatIndex <= oneNineSix) {
            return true;
        }
        if (twoOneZero <= formatIndex && formatIndex <= twoOneThree) {
            return true;
        }
        if (twoZeroEight == formatIndex) {
            return true;
        }
        return false;
    }

    /**
     * 验证时间格式
     *
     * @param
     * @return java.lang.Boolean
     * @author jichao
     * @date 2019/09/09 11:46
     */
    private Boolean checkTime(int formatIndex) {

        if (formatIndex == TwoZero) {
            return true;
        }
        if (formatIndex == TwoOne) {
            return true;
        }
        if (formatIndex == ThreeTwo) {
            return true;
        }
        if (formatIndex == oneEightThree) {
            return true;
        }
        if (twoZeroZero <= formatIndex && formatIndex <= twoZeroNine) {
            return true;
        }
        return false;
    }

    /**
     * 取出第一行数据，确定表头列的索引，用来确定数据列结束位置,找到第一个为空的表头长度作为列长
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

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (vIsOpen) {
            value.append(ch, start, length);
        }
    }

    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }
}

