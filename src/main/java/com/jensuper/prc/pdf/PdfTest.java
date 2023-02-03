package com.jensuper.prc.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * pdf
 * </p>
 *
 * @author jichao
 * @date 2023/3/21 16:00
 */
public class PdfTest {
    private static String PDFPATH = "/Users/chaoji/jensuper/资料/崔玉涛育儿百科.pdf";
    private static String FILEPATH = "D:/Maven权威指南中文版.doc";
    public static void main(String[] args)  {
        String content=getPdfContent(PDFPATH);
        System.out.println(content);

//        toFile(PDFPATH,FILEPATH);
    }
    /**
     * 获取pdf的内容
     * @param pdfPath
     * @return
     */
    private static String getPdfContent(String pdfPath) {
        PdfReader reader = null;
        StringBuffer buff = new StringBuffer();
        try {
            reader = new PdfReader(pdfPath);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            int num = reader.getNumberOfPages();// 获得页数
            TextExtractionStrategy strategy;
            for (int i = 1; i <= num; i++) {
                strategy = parser.processContent(i,
                        new SimpleTextExtractionStrategy());
                buff.append(strategy.getResultantText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buff.toString();
    }
    /**
     * 将对应的pdf文件读到指定的文件中
     * @param pdfPath
     * @param filePath
     */
    private static void toFile(String pdfPath, String filePath) {
        PrintWriter writer = null;
        PdfReader reader = null;
        try {
            writer = new PrintWriter(new FileOutputStream(filePath));
            reader = new PdfReader(pdfPath);
            int num = reader.getNumberOfPages();// 获得页数
            System.out.println("Total Page: " + num);
            StringBuffer content = new StringBuffer(""); // 存放读取出的文档内容
            for (int i = 1; i <= num; i++) {
                // 读取第i页的文档内容
                content.append(PdfTextExtractor.getTextFromPage(reader, i));
            }
            writer.write(content.toString());// 写入文件内容
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
