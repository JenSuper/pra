package com.jensuper.prc.fileencode;

import java.io.*;

/**
 * @author jichao
 * @version V1.0
 * @description: 获取文件编码格式
 * @date 2019/09/12
 */
public class Excel {

    /**
     * 文件编码格式
     */

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Admin\\Desktop\\测试文件\\维度列有空值.csv");
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(getFileCode(inputStream));

        try {
            System.out.println(codeString("C:\\Users\\Admin\\Desktop\\测试文件\\日期小数分数特殊字符.csv"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
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
}
