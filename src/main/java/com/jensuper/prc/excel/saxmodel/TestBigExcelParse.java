package com.jensuper.prc.excel.saxmodel;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/06
 */
public class TestBigExcelParse {
    private OPCPackage xlsxPackage;
    private int minColumns;
    private PrintStream output;
    private String sheetName;

    //构造器
    public TestBigExcelParse(OPCPackage pkg, PrintStream output,
                             String sheetName, int minColumns) {
        this.xlsxPackage = pkg;
        this.output = output;
        this.minColumns = minColumns;
        this.sheetName = sheetName;
    }

    /**
     * @param path----文件路径
     * @param sheetName---解析的sheet名称
     * @param minColumns---excel的最大列数
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> parseSheet(String path, String sheetName, int minColumns) throws Exception {
        //文件地址

//        OPCPackage pkg = OPCPackage.open(path, PackageAccess.READ);
        OPCPackage pkg = OPCPackage.open(new FileInputStream(path));
        XSSFReader r = new XSSFReader(pkg);
        //解析的sheet名称
        // InputStream rId1 = r.getSheet("rId1");//第一个sheet表
        XSSFReader.SheetIterator sheetsData = (XSSFReader.SheetIterator) r.getSheetsData();
        SharedStringsTable sst = r.getSharedStringsTable();
        StylesTable styles = r.getStylesTable();
        XMLReader parser = XMLReaderFactory.createXMLReader();
        BigExcelParse handler = new BigExcelParse(styles, sst, minColumns);
        parser.setContentHandler(handler);
        //遍历---获取指定的sheet名称
        HashMap<String, Object> map = new HashMap<String, Object>();
        while (sheetsData.hasNext()) {
            InputStream in = sheetsData.next();
            if (sheetName.equals(sheetsData.getSheetName())) {
                InputSource inputSource = new InputSource(in);
                parser.parse(inputSource);
//                List<String[]> rows = handler.getRows();//返回所有的封装结果
                map.put("success", true);
                map.put("msg", "解析完成！");
//                map.put("rows", rows);
                in.close();
            }
        }
        if (map.isEmpty()) {
            map.put("success", false);
            map.put("msg", "解析失败，没有找到相应的sheet表！");
        }
        return map;
    }

    /**
     * 测试方法调用
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        HashMap<String, Object> res = parseSheet("C:\\Users\\Admin\\Desktop\\测试文件\\100M大文件.xlsx", "Sheet1", 285);
        System.out.println((String) res.get("msg"));
        List<String[]> rows = (List<String[]>) res.get("rows");
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\shea\\Desktop\\测试_bigExcel.txt"));
        for (String[] row : rows
                ) {
            for (String cell : row
                    ) {
                out.write(cell + "\t");
            }
            out.newLine();
        }
        out.flush();
        out.close();
    }

}
