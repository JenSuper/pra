package com.jensuper.prc.demo;

import com.jensuper.prc.date.DateStyle;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;
import org.springframework.util.StopWatch;
import sun.util.calendar.BaseCalendar;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/22
 */
public class Demo {

    /**
     * 计时器
     */
    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("开始任务");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void test2() throws IOException {
        Object a = 03;
        Integer b = 3;
        System.out.println(a==b);
    }
}
