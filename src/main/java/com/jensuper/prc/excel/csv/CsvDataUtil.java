package com.jensuper.prc.excel.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/09/16
 */
public class CsvDataUtil {

    private String fileCode = "utf-8";


    public List<String[]> readCsv(InputStream inputStream) throws Exception {
        // 获取文件编码格式
        InputStreamReader streamReader = new InputStreamReader(inputStream, fileCode);
        CSVReader reader = new CSVReader(streamReader);
        List<String[]> listData = reader.readAll();
        return listData;
    }

    public String codeString(InputStream inputStream) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(inputStream);
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        // 其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    /**
     * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static InputStream getInputStream(InputStream in) throws IOException {

        PushbackInputStream testin = new PushbackInputStream(in);
        int ch = testin.read();
        if (ch != 0xEF) {
            testin.unread(ch);
        } else if ((ch = testin.read()) != 0xBB) {
            testin.unread(ch);
            testin.unread(0xef);
        } else if ((ch = testin.read()) != 0xBF) {
            throw new IOException("错误的UTF-8格式文件");
        } else {
        }
        return testin;
    }

    @Test
    public void test(){
        String path = "C:\\Users\\Admin\\Desktop\\测试文件\\订单列表20190723.csv";
        FileInputStream inputStream = null;
        FileInputStream inputStream2 = null;
        try {
            inputStream = new FileInputStream(new File(path));
            inputStream2 = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileCode = this.codeString(inputStream2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List<String[]> listData = readCsv(inputStream);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @Test
    public void test2323(){
        String s = "";
        System.out.println("".equals(s));
        System.out.println(s.length());

    }
}
