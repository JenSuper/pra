package com.jensuper.prc.bigone;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 品牌合并关键词
 * </p>
 *
 * @author jichao
 * @date 2022/1/13 16:39
 * @since
 */
public class DataSourceBrand {

    public static void main(String[] args) throws IOException {
        String sourcePath = "/Users/chaoji/devlop/project/小红书品牌.csv";
        String sinkPath = "/Users/chaoji/devlop/project/小红书品牌_合并.csv";

        // 合并重复品牌对应的关键词：nike "xx1"~"xxx2"~"xxx3"
        List<String> readLines = FileUtils.readLines(new File(sourcePath), "utf-8");

        LinkedHashMap<String, String> brandMap = new LinkedHashMap<>();
        readLines.stream().skip(1).forEach(line->{
            String[] split = line.split(",");
            String brand = split[0];
            String key = split[1];

            if (brandMap.containsKey(brand)) {
                brandMap.put(brand, brandMap.get(brand) + "~" + addSymbol(key));
            }else{
                brandMap.put(brand, addSymbol(key));
            }
        });

        List<String> listOut = new ArrayList<>();
        brandMap.forEach((k,v)->{
            listOut.add(k + "," + v);
        });
        FileUtils.writeLines(new File(sinkPath), listOut);
    }

    public static String addSymbol(String key) {
        return "\"" + key + "\"";
    }
}
