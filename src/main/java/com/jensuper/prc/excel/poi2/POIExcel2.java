//package com.jensuper.prc.excel.poi2;
//
//
//import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Init;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// * @author jichao
// * @version V1.0
// * @description:
// * @date 2019/09/04
// */
//public class POIExcel2 {
//    private int totalRows = 0;// 总行数
//    private int totalCells = 0;// 总列数
//
//    public Map<String, List<List<String>>> read(String fileName) {
//        Map<String, List<List<String>>> maps = new HashMap<String, List<List<String>>>();
//        if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$"))
//            return maps;
//        File file = new File(fileName);
//        if (file == null || !file.exists())
//            return maps;
//        try {
//            Workbook wb = WorkbookFactory.create(new FileInputStream(file));
//            try {
//                maps = read(wb);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return maps;
//    }
//
//    public int getTotalRows() {
//        return totalRows;
//    }
//
//    public int getTotalCells() {
//        return totalCells;
//    }
//    private static XSSFFormulaEvaluator formulaEvaluator;
//    private Map<String, List<List<String>>> read(Workbook wb) throws ExecutionException, InterruptedException {
//        ExecutorService service = Executors.newCachedThreadPool();
//        formulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) wb);
//        Map<String, List<List<String>>> maps = new HashMap<String, List<List<String>>>();
//        int number = wb.getNumberOfSheets();
//        if (number > 0) {
//            for (int i = 0; i < number; i++) { // 循环每个工作表
//                Sheet sheet = wb.getSheetAt(i);
//                Future submit = service.submit(new ExcelReadTask(totalRows, totalCells, sheet));
//                maps.put(sheet.getSheetName(), (List<List<String>>)submit.get());
//            }
//        }
//        if (service.isShutdown()) {
//            service.shutdown();
//        }
//        return maps;
//    }
//
//    /*
//     * private String getRightStr(String sNum) { DecimalFormat decimalFormat =
//     * new DecimalFormat("##.00"); String resultStr = decimalFormat.format(new
//     * Double(sNum)); if (resultStr.matches("^[-+]?\\d+\\.[0]+$")) { resultStr =
//     * resultStr.substring(0, sNum.indexOf(".")); } return resultStr; }
//     */
//
//    public String getCell(Cell cell) {
//        String date = formartDate(cell);
//        if (!StringUtils.isEmpty(date)) {
//            return date;
//        }
//        if (cell.getCellTypeEnum().equals(CellType.FORMULA)) {
//            String value = String.valueOf(formulaEvaluator.evaluate(cell).getNumberValue());
//            return formatDateToString(value);
//        }
//        /*
//         * if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) { if
//         * (HSSFDateUtil.isCellDateFormatted(cell)) { cellValue =
//         * getRightStr(cell.getDateCellValue() + ""); } else {
//         *
//         * cellValue = getRightStr(cell.getNumericCellValue() + ""); } } else if
//         * (Cell.CELL_TYPE_STRING == cell.getCellType()) { cellValue =
//         * cell.getStringCellValue(); } else if (Cell.CELL_TYPE_BOOLEAN ==
//         * cell.getCellType()) { cellValue = cell.getBooleanCellValue() + ""; }
//         * else { cellValue = cell.getStringCellValue(); }
//         */
//        HSSFDataFormatter hSSFDataFormatter = new HSSFDataFormatter();
//        String cellValue = hSSFDataFormatter.formatCellValue(cell); // 使用EXCEL原来格式的方式取得值
//        // 处理特殊情况
//        if ("- 0".equals(cellValue)) {
//            return "0";
//        }
//        return cellValue;
//    }
//
//    private String formartDate(Cell cell) {
//        if (cell.getCellTypeEnum().equals(CellType.NUMERIC) && DateUtil.isCellDateFormatted(cell)) {
//            short format = cell.getCellStyle().getDataFormat();
//            SimpleDateFormat sdf = null;
//            if (format == 14 || format == 31 || format == 57 || format == 58) {
//                //日期
//                sdf = new SimpleDateFormat("yyyy-MM-dd");
//            } else if (format == 20 || format == 32) {
//                //时间
//                sdf = new SimpleDateFormat("HH:mm");
//            }
//            double value = cell.getNumericCellValue();
//            Date date = DateUtil.getJavaDate(value);
//            return sdf.format(date);
//        }
//        return null;
//    }
//
//    /** *
//     * 四舍五入，保留2位
//     * @author jichao
//     * @date 2019/09/04 14:31
//     * @param param
//     * @return java.lang.String
//     */
//    private String formatDateToString(String param) {
//        BigDecimal bigDecimal = new BigDecimal(param);
//        BigDecimal decimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
//        return ObjectUtils.nullSafeToString(decimal);
//    }
//
//    public static void main(String[] args) {
//        Date date = new Date();
//        try {
//            Map<String, List<List<String>>> map = new POIExcel2()
//                    .read("C:\\Users\\Admin\\Desktop\\测试文件\\100M大文件.xlsx");
//            System.out.println(map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Start====>"+date);
//        System.out.println("END====>"+new Date());
//    }
//}
//
