package com.jensuper.prc.excel.poi;


import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.junit.Test;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 导出excel
 * @date 2019/09/17
 */
public class WriteExcel {


    /**
     * 导出数据
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        // keep 100 rows in memory, exceeding rows will be flushed to disk
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sh.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }
        FileOutputStream out = new FileOutputStream("C:\\Users\\Admin\\Desktop\\sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }



    /**
     * 导出图片-方式1
     * @throws IOException
     */
    public void createPic1() throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        BufferedImage bufferImg = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\首页.svg"));
        ImageIO.write(bufferImg, "svg", byteArrayOut);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        //获取到poi的画图工具
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        //将嵌入的图片的位置固定好
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 10,
                4, (short) 11, 5);
        //写入图片到excel
        patriarch.createPicture(anchor, wb.addPicture(
                byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        //如果需要直接下载
//        response.setHeader("Content-Disposition", "attachment;filename=" + new String((自定义excel名称).getBytes("gbk"), "iso8859-1") + ".xls");
//        wb.write(response.getOutputStream());
//        wb.close();
        //如果不需要下载就将下载换位保存到本地
        //保存到本地或项目下
        OutputStream os = new FileOutputStream("d:/ab.xls");
        wb.write(os);
        os.close();
    }

    @Test
    public void test2() throws IOException {
        createPic2();
    }

    /**
     * 导出图片-方式2
     */
    public void createPic2() {
        FileOutputStream fileOut = null;
        BufferedImage bufferImg = null;
        //先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        try {
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Admin\\Desktop\\首页2.svg");
//            byte[] bytes = testByte(fileInputStream);
//            bufferImg = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\首页.svg"));
            BufferedImage rasterize = rasterize(new File("C:\\Users\\Admin\\Desktop\\首页2.svg"));
            ImageIO.write(rasterize, "svg", byteArrayOut);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet1 = wb.createSheet("test picture");
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
            //anchor主要用于设置图片的属性
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 1, 1, (short) 5, 8);
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
//            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023,100,(short) 1, 1, (short)5, 8);
//            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
            //插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
            fileOut = new FileOutputStream("D:/测试Excel.xls");
            // 写入excel文件
            wb.write(fileOut);
            System.out.println("----Excle文件已生成------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Test
    public void test123(){
        try {
            rasterize(new File("C:\\Users\\Admin\\Desktop\\首页2.svg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage rasterize(File svgFile) throws IOException {

        final BufferedImage[] imagePointer = new BufferedImage[1];

        // Rendering hints can't be set programatically, so
        // we override defaults with a temporary stylesheet.
        // These defaults emphasize quality and precision, and
        // are more similar to the defaults of other SVG viewers.
        // SVG documents can still override these defaults.
        String css = "svg {" +
                "shape-rendering: geometricPrecision;" +
                "text-rendering:  geometricPrecision;" +
                "color-rendering: optimizeQuality;" +
                "image-rendering: optimizeQuality;" +
                "}";
        File cssFile = File.createTempFile("batik-default-override-", ".css");
        FileUtils.writeStringToFile(cssFile, css);

        TranscodingHints transcoderHints = new TranscodingHints();
        transcoderHints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
        transcoderHints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION,
                SVGDOMImplementation.getDOMImplementation());
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI,
                SVGConstants.SVG_NAMESPACE_URI);
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
        transcoderHints.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, cssFile.toURI().toString());

        try {

            TranscoderInput input = new TranscoderInput(new FileInputStream(svgFile));

            ImageTranscoder t = new ImageTranscoder() {

                @Override
                public BufferedImage createImage(int w, int h) {
                    return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                }

                @Override
                public void writeImage(BufferedImage image, TranscoderOutput out)
                        throws TranscoderException {
                    imagePointer[0] = image;
                }
            };
            t.setTranscodingHints(transcoderHints);
            t.transcode(input, null);
        }
        catch (TranscoderException ex) {
            // Requires Java 6
            ex.printStackTrace();
            throw new IOException("Couldn't convert " + svgFile);
        }
        finally {
            cssFile.delete();
        }

        return imagePointer[0];
    }

    private byte[] testByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;

    }

    /**--------------------------------------  我是分割线--------------------------------------------------*/
    @Test
    public void test(){
        this.writeData();
    }

    /**
     * 写入数据及导入图片
     */
    public void writeData() {
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
        // 输出
        try {
            out = new FileOutputStream("C:\\Users\\Admin\\Desktop\\sxssf.xlsx");
            wb.write(out);
            out.close();
            System.out.println("----Excle文件已生成------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 释放磁盘临时文件
        wb.dispose();
    }
}
