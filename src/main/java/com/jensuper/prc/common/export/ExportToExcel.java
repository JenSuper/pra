package com.jensuper.prc.common.export;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description: 导出excel
 * @date 2019/09/18
 */
@Component
@Slf4j
public class ExportToExcel {

    /**
     * 写入数据及导入图片
     *
     * @param mapData   数据:key 名称 value 数据
     * @param sheetName 图片名称
     * @param response  响应结果
     * @return void
     * @author jichao
     * @date 2019/09/18 15:47
     */
    public void export(Map<String, List<List<String>>> mapData, String sheetName, String fileType, InputStream inputStream, HttpServletResponse response) {
        // 每100行会刷新到磁盘中
        HSSFWorkbook wb = new HSSFWorkbook();
        if (StringUtils.isBlank(sheetName)) {
            sheetName = "default";
        }
        // 写入数据
        for (Map.Entry<String, List<List<String>>> entry : mapData.entrySet()) {
            this.writeData(wb, entry.getKey(), entry.getValue());
        }
        this.writePic(wb, sheetName, fileType, inputStream);
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/vnd.ms-excel");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(sheetName+".xls", "utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
//            throw new CustomException(DashboardEnum.DASHBOARD_EXPORT_ERROR.getCode(), e.getMessage());
        }
        // 释放磁盘临时文件
//        wb.dispose();
        log.info("dashboard export excel success");
    }

    /**
     * 写入数据
     *
     * @param wb
     * @param tableName
     * @param listData
     * @return void
     * @author jichao
     * @date 2019/09/18 15:51
     */
    private void writeData(HSSFWorkbook wb, String tableName, List<List<String>> listData) {
        if (CollectionUtils.isEmpty(listData)) {
            return;
        }
        // 将数据写入wb中
        HSSFSheet sh = wb.createSheet(tableName);

        // 所有行
        for (int rownum = 0; rownum < listData.size(); rownum++) {
            List<String> cellList = listData.get(rownum);
            Row row = sh.createRow(rownum);
            // 所有列
            for (int cellnum = 0; cellnum < cellList.size(); cellnum++) {
                String cellStr = cellList.get(cellnum);
                Cell cell = row.createCell(cellnum);
                cell.setCellValue(cellStr);
            }
        }
    }

    /**
     * 写入图片
     *
     * @param wb
     * @param sheetName
     * @param inputStream
     * @return void
     * @author jichao
     * @date 2019/09/18 15:51
     */
    private void writePic(HSSFWorkbook wb, String sheetName, String fileType, InputStream inputStream) {
        BufferedImage bufferImg = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            bufferImg = ImageIO.read(inputStream);
            ImageIO.write(bufferImg, fileType, byteArrayOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.createSheet(sheetName);
        //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        //anchor主要用于设置图片的属性
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 5, 8);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        //插入图片
        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), SXSSFWorkbook.PICTURE_TYPE_JPEG));
    }
}
