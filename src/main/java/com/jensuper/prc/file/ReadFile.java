package com.jensuper.prc.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 读文件
 * </p>
 *
 * @author jichao
 * @date 2021/11/1 22:24
 * @since
 */
public class ReadFile {
    /**
     * 只需要修改【listPath】和 【outFile】
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // 定义要读的文件夹路径
        List<String> listPath = new ArrayList<String>(){
            {
                add("/Users/chaoji/devlop/project/bolt/dashboard_server/src/main/java/com/bigonelab/dashboard/controller/edis");
                add("/Users/chaoji/devlop/project/bolt/dashboard_server/src/main/java/com/bigonelab/dashboard/service/edis");
                add("/Users/chaoji/devlop/project/bolt/dashboard_server/src/main/java/com/bigonelab/dashboard/service/impl/edis");
            }
        };

        // 输出文件路径
        File outFile = new File("/Users/chaoji/devlop/project/test/code.txt");
        List<String> out = new ArrayList<>();

        // read file
        listPath.forEach(path->{
            File readFile = new File(path);
            File[] files = readFile.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File file : files) {
                List<String> strings = null;
                try {
                    strings = FileUtils.readLines(file, "utf-8");
                } catch (IOException e) {
                    System.out.println("readLines error...");
                    continue;
                }
                strings.removeIf(StringUtils::isBlank);
                out.addAll(strings);
            }
        });

        // write file
        try {
            FileUtils.writeLines(outFile, out,false);
        } catch (IOException e) {
            System.out.println("writeLines error...");
        }
    }
}
