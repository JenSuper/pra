package com.jensuper.prc.common.excel;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/04
 */
public class ReaderFactory {

    /**
     * excel2003扩展名
     */
    public static final String EXCEL03_EXTENSION = "xls";
    /**
     * excel2007扩展名
     */
    public static final String EXCEL07_EXTENSION = "xlsx";

    /**
     * 读取Excel文件，可能是03也可能是07版本
     *
     * @param inputStream
     * @param fileType
     * @return java.util.Map<java.lang.String       ,       java.util.List       <       java.util.List       <       java.lang.String>>>
     * @author jichao
     * @date 2019/09/10 17:01
     */
    public static Map<String, List<List<String>>> readExcel(InputStream inputStream, String fileType) {
        Map<String, List<List<String>>> mapRet = new HashMap<>();
        // 处理excel2003文件
        if (fileType.equals(EXCEL03_EXTENSION)) {
            ExcelXlsReader exceXls = new ExcelXlsReader();
            mapRet = exceXls.process(inputStream);
            // 处理excel2007文件
        } else if (fileType.equals(EXCEL07_EXTENSION)) {
            mapRet = readDataStructure(inputStream);
        }
        // 去掉空行
        mapRet = TextDataUtil.clearEmptyRows(mapRet);
        return mapRet;
    }

    /**
     * 读取07版excel
     *
     * @param inputStream
     * @return java.util.Map<java.lang.String   ,   java.util.List   <   java.util.List   <   java.lang.String>>>
     * @author jichao
     * @date 2019/09/11 10:17
     */
    public static Map<String, List<List<String>>> readDataStructure(InputStream inputStream) {
        Map<String, List<List<String>>> mapRet = new LinkedHashMap<>();
        try {
            //文件地址
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFReader r = new XSSFReader(pkg);
            //解析的sheet名称及数据
            XSSFReader.SheetIterator sheetsData = (XSSFReader.SheetIterator) r.getSheetsData();
            SharedStringsTable sst = r.getSharedStringsTable();
            StylesTable styles = r.getStylesTable();
            XMLReader parser = XMLReaderFactory.createXMLReader();
            //遍历---获取sheet对应的数据
            while (sheetsData.hasNext()) {
                ExcelXlsxReader handler = new ExcelXlsxReader(styles, sst, 500);
                parser.setContentHandler(handler);
                InputStream in = sheetsData.next();
                InputSource inputSource = new InputSource(in);
                parser.parse(inputSource);
                //返回所有的封装结果
                List<List<String>> rows = handler.getRows();
                mapRet.put(sheetsData.getSheetName(), rows);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }
        return mapRet;
    }

}
