package com.jensuper.prc.dowload;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/21
 */
@RestController
public class ExportController {

    @GetMapping("/index")
    public String index(HttpServletResponse response) throws IOException {
        return "index";
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String sheetName = "sheet";
            XSSFSheet sheet = wb.createSheet(sheetName);
            wb.write(response.getOutputStream());
        } finally {
            wb.close();
        }
    }

    /**
     * 写入数据及导入图片
     */
    @GetMapping("/exoprtExcel")
    public void writeData(HttpServletResponse response) throws IOException {
        // 每100行会刷新到磁盘中
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        // 写入数据
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }
        // 写入图片
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        FileOutputStream out = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            bufferImg = ImageIO.read(new File("C:\\Users\\Admin\\Pictures\\Saved Pictures\\0.jpg"));
            ImageIO.write(bufferImg, "jpg", byteArrayOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SXSSFSheet sheet1 = wb.createSheet("test picture");
        //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        SXSSFDrawing patriarch = sheet1.createDrawingPatriarch();
        //anchor主要用于设置图片的属性
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 5, 8);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        //插入图片
        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), SXSSFWorkbook.PICTURE_TYPE_JPEG));
        //导出浏览器
        String fileName = "1.xls";
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);

        // 输出
//        try {
//            out = new FileOutputStream("C:\\Users\\Admin\\Desktop\\sxssf.xlsx");
//            wb.write(out);
//            out.close();
//            System.out.println("----Excle文件已生成------");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fileOut != null) {
//                try {
//                    fileOut.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        // 释放磁盘临时文件
        wb.dispose();
    }
}
