package com.jensuper.prc.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 读json
 * </p>
 *
 * @author jichao
 * @date 2021/11/5 13:57
 * @since
 */
public class RedsJSON {

    @Test
    public void test() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime1 = LocalDateTime.parse("2021-11-08 23:02", formatter);
        long milli = LocalDateTime.from(localDateTime1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(milli);
    }

    @Test
    public void test2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String format = dtf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(1635606120000L), ZoneId.systemDefault()));
        System.out.println(format);
    }

    @Test
    public void test3() {
        LocalDate now = LocalDate.now();
        LocalDate of = LocalDate.of(2021, 11, 11);
        System.out.println(now.compareTo(of));

    }
}
