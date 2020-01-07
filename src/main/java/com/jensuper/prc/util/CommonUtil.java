package com.jensuper.prc.util;

import org.junit.Test;

import java.io.IOException;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/11/18
 */
public class CommonUtil {

    @Test
    public void excelColStrToNum() {
        String colstr = "C";
        int colIndex = excelColStrToNum(colstr, colstr.length());
        System.out.println("'" + colstr + "' column index of " + colIndex);
    }

    /**
     * Excel column index begin 1
     * @param colStr
     * @param length
     * @return
     */
    public static int excelColStrToNum(String colStr, int length) {
        int num = 0;
        int result = 0;
        for(int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (int)(ch - 'A' + 1) ;
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }
}
