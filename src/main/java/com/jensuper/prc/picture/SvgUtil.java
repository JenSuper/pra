package com.jensuper.prc.picture;

import java.io.*;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;

public class SvgUtil {

    public static void convertSVGFile2Pdf(File svg, File pdf) throws IOException {
        InputStream in = new FileInputStream(svg);
        OutputStream out = new FileOutputStream(pdf);
        out = new BufferedOutputStream(out);
//        convert2Pdf(in, out);
    }

//    public static void convert2Pdf(InputStream in, OutputStream out) throws IOException {
//        Transcoder tr = new PDFTranscoder();
//        try {
//            TranscoderInput input = new TranscoderInput(in);
//            try {
//                TranscoderOutput output = new TranscoderOutput(out);
//                tr.transcode(input, output);
//            } catch (TranscoderException e) {
//                e.printStackTrace();
//            } finally {
//                out.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            in.close();
//        }
//    }

    public static void convertSVGFile2Png(File svg, File pdf) throws IOException {
        InputStream in = new FileInputStream(svg);
        OutputStream out = new FileOutputStream(pdf);
        out = new BufferedOutputStream(out);
        convert2PNG(in, out);
    }
    public static void convertSVGFile2PngInput(InputStream in, File pdf) throws IOException {
//        InputStream in = new FileInputStream(svg);
        OutputStream out = new FileOutputStream(pdf);
        out = new BufferedOutputStream(out);
        convert2PNG(in, out);
    }

    public static void convert2PNG(InputStream in, OutputStream out) throws IOException {
        Transcoder tr = new PNGTranscoder();
        try {
            TranscoderInput input = new TranscoderInput(in);
            try {
                TranscoderOutput output = new TranscoderOutput(out);
                tr.transcode(input, output);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    public static void convertStr2Pdf(String svg, File pdf) throws IOException {
        InputStream in = new ByteArrayInputStream(svg.getBytes());
        OutputStream out = new FileOutputStream(pdf);
        out = new BufferedOutputStream(out);
//        convert2Pdf(in, out);
    }

    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Admin\\Desktop\\首页.svg");
        File destFile = new File("C:\\Users\\Admin\\Desktop\\首页222.png");
        convertSVGFile2Png(f, destFile);
        SvgUtil svgUtil = new SvgUtil();
//        String svgCode = FileUtils.readFileToString(new File("C:\\Users\\Admin\\Desktop\\test.txt"), "utf-8");
//        svgCode = svgCode.replaceAll("\r", "").replace("\n", "");
//        saveSvgToPng(svgCode, "C:\\Users\\Admin\\Desktop\\首页33.svg");
    }

    public static void saveSvgToPng(String content, String path) {
        InputStream svgFileStream;
        try {
            svgFileStream = new ByteArrayInputStream(content.getBytes("UTF-8"));
        } catch (Exception e) {
            svgFileStream = new ByteArrayInputStream(content.getBytes());
        }
        TranscoderInput inputSvgImage = new TranscoderInput(svgFileStream);
        PNGTranscoder converter = new PNGTranscoder();
        FileOutputStream pngFileStream;
        try {
            pngFileStream = new FileOutputStream(path);
            TranscoderOutput outputPngImage = new TranscoderOutput(pngFileStream);
            converter.transcode(inputSvgImage, outputPngImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
//            log.error("FileOutputStream new error:{}" + e);
        } catch (TranscoderException e) {
            e.printStackTrace();
//            log.error("TranscoderException error:{}" + e);
        }
    }

    public static void main123(String[] args) throws IOException {
        String svgCode = FileUtils.readFileToString(new File("C:\\Users\\Admin\\Desktop\\test.txt"), "utf-8");
        base64ChangeImage(svgCode, "C:\\Users\\Admin\\Desktop\\ss.svg");
    }

    /**
     * BASE转图片
     * @param baseStr  base64字符串
     * @param imagePath 生成的图片
     * @return
     */
    public static boolean base64ChangeImage(String baseStr,String imagePath){
        if (baseStr == null){
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(baseStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imagePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}



