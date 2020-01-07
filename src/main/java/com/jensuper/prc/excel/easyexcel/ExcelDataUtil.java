package com.jensuper.prc.excel.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description: 操作文本数据
 * @date 2019/09/02
 */
public class ExcelDataUtil {


    /**
     * 读取excel表结构
     *
     * @param inputStream
     * @return java.util.List<java.lang.String>
     * @author jichao
     * @date 2019/09/02 17:05
     */
    public static Map<String, List<List<String>>> readDataStructure(InputStream inputStream) {
        // 返回结果
        Map<String, List<List<String>>> mapRet = new LinkedHashMap<>();
        // 读取excel
        StringExcelListener listener = new StringExcelListener();
        ExcelReader reader = EasyExcelFactory.getReader(inputStream, listener);
        // 获取每一个sheet
        List<Sheet> sheets = reader.getSheets();
        for (Sheet sheet : sheets) {
            List<List<String>> listData = listener.getDatas();
            mapRet.put(sheet.getSheetName(), listData);
            // 每次需要清空，否则数据会追加
//            listener.clearDatas();
        }
        return mapRet;
    }

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\测试文件\\100M大文件 - 副本.xlsx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, List<List<String>>> map = readDataStructure(inputStream);
        System.out.println(map);
    }

    @Test
    public void test(){
        String fileName = "C:\\Users\\Admin\\Desktop\\测试文件\\100M大文件.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName, null, new StringExcelListener()).build();
        ReadSheet readSheet1 = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet1);
        Integer sheetNo = readSheet1.getSheetNo();
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }
}
