package com.jensuper.prc.excel.poi2;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/06
 */
public class ExcelReadTask implements Callable {

    private int totalRows;
    private int totalCells;
    private Sheet sheet;

    public ExcelReadTask(int totalRows, int totalCells, Sheet sheet) {
        this.totalRows = totalRows;
        this.totalCells = totalCells;
        this.sheet = sheet;
    }

    @Override
    public List<List<String>> call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return null;
    }

//    @Override
//    public List<List<String>> call() throws Exception {
//        POIExcel2 poiExcel2 = new POIExcel2();
//        List<List<String>> list = new ArrayList<List<String>>();
//        int delnumber = 0;// 第一页去除行数
//        this.totalRows = sheet.getPhysicalNumberOfRows() - delnumber; // 获取工作表中行数
//        if (this.totalRows >= 1 && sheet.getRow(delnumber) != null) {
//            this.totalCells = sheet.getRow(0)
//                    .getPhysicalNumberOfCells(); // 得到当前行的所有单元格
//            for (int j = 0; j < totalRows; j++) {
//                List<String> rowLst = new ArrayList<String>();
//                for (int f = 0; f < totalCells; f++) {
//                    if (totalCells > 0) {
//                        if (sheet.getRow(j) == null) {
//                            continue;
//                        }
//                        String value = poiExcel2.getCell(sheet.getRow(j).getCell(f));
//                        rowLst.add(value);
//                    }
//                }
//                list.add(rowLst);
//            }
//        }
//        return list;
//    }
}
