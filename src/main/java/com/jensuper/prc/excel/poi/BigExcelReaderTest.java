package com.jensuper.prc.excel.poi;

import com.jensuper.prc.excel.saxmodel.BigExcelParse;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class BigExcelReaderTest {
    public static void main(String[] args) throws Exception {
        Date date = new Date();
        String filepath = "C:\\Users\\Admin\\Desktop\\测试文件\\100M大文件.xlsx";
        parseSheet(filepath, "sheet", 1);
        File tempFile = new File(filepath);
//传入一个路径产生流再将流传入工具类，返回解析对象，Excel的所有数据就被解析到List<String[]> 里面，遍历list任由你处置。
        FileInputStream inputStream = new FileInputStream(tempFile);
        ExcelParser excelParser = new ExcelParser();
        ExcelParser parse = excelParser.parse(inputStream);
        List<String[]> datas = parse.getDatas();
        System.out.println(datas.size());
        System.out.println("Start====>" + date);
        System.out.println("END====>" + new Date());
        System.out.println();
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
        OPCPackage pkg = OPCPackage.open(path, PackageAccess.READ);
        XSSFReader r = new XSSFReader(pkg);
        //解析的sheet名称
        XSSFReader.SheetIterator sheetsData = (XSSFReader.SheetIterator) r.getSheetsData();
        SharedStringsTable sst = r.getSharedStringsTable();
        StylesTable styles = r.getStylesTable();
        XMLReader parser = XMLReaderFactory.createXMLReader();
//        BigExcelParse handler = new BigExcelParse(styles, sst, 100);
//        BigExcelParse handler = new BigExcelParse(styles, sst, minColumns);
//        parser.setContentHandler(handler);
        //遍历---获取指定的sheet名称
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<>();
//        while (sheetsData.hasNext()) {
//            list.add(sheetsData.getSheetName());
//        }
        while (sheetsData.hasNext()) {
            BigExcelParse handler = new BigExcelParse(styles, sst, 100);
            parser.setContentHandler(handler);
            InputStream in = sheetsData.next();
            InputSource inputSource = new InputSource(in);
            parser.parse(inputSource);
            List<List<String>> rows = handler.getRows();//返回所有的封装结果
            System.out.println(sheetsData.getSheetName()+"==>"+rows.size());
            map.put("rows", rows);
            in.close();
        }
        return map;
    }


    @Test
    public void test(){
        String filepath = "C:\\Users\\Admin\\Desktop\\测试文件\\日期小数分数特殊字符.xlsx";
        try {
            parseSheet(filepath, "sheet", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test123(){
        String s = "2019\"年\"1\"月\"1\"日\"";
        if (s.contains("\"")) {
            String s1 = s.replaceAll("\"", "");
            System.out.println(s1);
        }


    }

}