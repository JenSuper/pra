package com.jensuper.prc.excel.readwrite;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseTest {

    @Test
    public void set() {

//        String file = "/Users/mac/Desktop/拯救人们2019-01-21 重庆时时彩.xlsx";
        String file = "C:\\Users\\Admin\\Desktop\\工时统计\\纪超.xlsx";
        File file1 = new File(file);
        try {
            //传入一个文件，调用readExcel()读取文件，返回List<ExcelSheetPO>
            List<ExcelSheetPO> list = ExcelUtils.readExcel(file1, null, null);
            for (ExcelSheetPO a : list) {
                System.out.println(a.getHeaders() + ".." + a.getTitle() + ".." + a.getSheetName());
                for (List<Object> b : a.getDataList()) {
                    System.out.println(b);
                }
            }
            //调用createWorkbookAtDisk()生成excel
            ExcelUtils.createWorkbookAtDisk(ExcelVersion.V2007, list, "C:\\Users\\Admin\\Desktop\\工时统计\\纪超-备份.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void export() throws IOException {

        String path = "C:\\Users\\Admin\\Desktop\\大数据产品部5月考勤";
        File file = new File(path);
        File[] files = file.listFiles();

        List<ExcelSheetPO> listAll = new ArrayList<>();
        for (File fileOne : files) {
            try {
                //传入一个文件，调用readExcel()读取文件，返回List<ExcelSheetPO>
                List<ExcelSheetPO> listCurrentData = ExcelUtils.readExcel(fileOne, null, null);
//                for (ExcelSheetPO a : list) {
//                    System.out.println(a.getHeaders() + ".." + a.getTitle() + ".." + a.getSheetName());
//                    for (List<Object> b : a.getDataList()) {
//                        System.out.println(b);
//                    }
//                }
                Iterator<ExcelSheetPO> iterator = listCurrentData.iterator();
                while (iterator.hasNext()) {
                    ExcelSheetPO next = iterator.next();
                    next.getDataList().remove(1);
                    next.getDataList().remove(1);
                    if (listAll.size()>0) {
                        next.getDataList().remove(1);
                        List<List<Object>> dataList = next.getDataList();
                        listAll.get(0).getDataList().addAll(next.getDataList());
                    }
                }
                if (listAll.size() == 0) {
                    listAll.addAll(listCurrentData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //调用createWorkbookAtDisk()生成excel
        ExcelUtils.createWorkbookAtDisk(ExcelVersion.V2007, listAll, "C:\\Users\\Admin\\Desktop\\大数据产品部5月考勤\\汇总.xlsx");

    }
}