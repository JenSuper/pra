package com.jensuper.prc.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: 多个任务执行日期格式化操作
 * @date 2020/09/16
 */
public class ThreadLocalNormalUse {

    /**
     * 1. 如果每次调用转换方法都创建一个工具类，会增加内存消耗
     * 2. 所有线程共用一份日期格式化工具类，会出现线程安全问题
     */
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(()->{
                String time = new ThreadLocalNormalUse().convertDate(finalI);
                System.out.println(time);
            });
        }
        executorService.shutdown();
    }

    /**
     * 日期格式化
     * @param time
     * @return
     */
    private String convertDate(int time) {
        Date date = new Date(time * 1000);
        return simpleDateFormat.format(date);
    }
}
