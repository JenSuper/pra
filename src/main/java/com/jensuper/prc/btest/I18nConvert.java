package com.jensuper.prc.btest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 中文转换为英文
 * </p>
 *
 * @author jichao
 * @date 2021/11/25 10:46
 * @since
 */
public class I18nConvert {


    public static void main(String[] args) throws IOException {
        I18nConvert.change();
    }

    private static String basePath = "/Users/chaoji/devlop/project/test/";
    private static String cnFile = basePath+"cnFile.txt";
    private static String enFile = basePath+"enFile.txt";
    private static String convertFile = basePath+"convertFile.txt";
    private static String outPutFile = basePath+"outFile.txt";
    private static String symbol = "=";

    public static void change() throws IOException {
        // 1. 读取中英文
        List<String> cnList = FileUtils.readLines(new File(cnFile), "UTF-8");
        List<String> enList = FileUtils.readLines(new File(enFile), "UTF-8");

        // 2. 读取需要修改的内容
        List<String> convertList = FileUtils.readLines(new File(convertFile), "UTF-8");

        // 3. 将内容修改
        List<String> listNew = new ArrayList<>();
        convertList.forEach(word -> {
            // 找到等号两边内容
            String[] split = word.split(symbol);
            String left = split[0];
            String right = split[1];

            // 找等号右边的英文
            String enContent = "";
            for (int i = 0; i < cnList.size(); i++) {
                String s = cnList.get(i).replace(":", "").replace("：", "");
                if (right.equals(s)) {
                    enContent = enList.get(i).replace(":", "").replace("：", "");
//                    System.out.println("cn:" + cnList.get(i) + "-en:" + enContent);
                    listNew.add(left + symbol + enContent);
                    break;
                }
            }
            if (StringUtils.isBlank(enContent)) {
                System.out.println(right);
                listNew.add(left + symbol + right);
            }
        });

        FileUtils.writeLines(new File(outPutFile), listNew);

        System.out.println("end");
    }
}
