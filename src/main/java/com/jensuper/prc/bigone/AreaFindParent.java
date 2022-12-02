package com.jensuper.prc.bigone;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地区
 * </p>
 *
 * @author jichao
 * @date 2021/12/28 22:12
 * @since
 */
public class AreaFindParent {

    public static void main(String[] args) throws IOException {
        List<String> strings = FileUtils.readLines(new File("/Users/chaoji/devlop/1.txt"), "utf-8");
        System.out.println();
    }
    
    @Test
    public void test() throws IOException {
        List<String> ret = new ArrayList<>();
        List<String> readLines = FileUtils.readLines(new File("/Users/chaoji/devlop/无标题.csv"), "utf-8");

        // %s parent_id %s 上一级 %s 当前id
        String sql = "update dict_area set parent_id = (\n" +
                "select id from (select id from dict_area where  origin_id = %s  and level = %s) as a  \n" +
                ") where id = %s";
        readLines.forEach(line->{
            String[] dataArr = line.split(",");
            String parentId = dataArr[6];
            Integer parentLevel = Integer.valueOf(dataArr[7]) - 1;
            String id = dataArr[0];
            if (parentId.equals("0")) {
                return;
            }
            String format = String.format(sql, parentId, parentLevel, id);
            System.out.println(format);
            ret.add(format + ";");
        });

        FileUtils.writeLines(new File("/Users/chaoji/devlop/out.txt"), ret);
    }
}
