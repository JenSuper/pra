package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jichao
 * @version V1.0
 * @description: ScheduledThread 定时线程池
 * @date 2020/08/30
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);

        // 只执行一次
//        threadPool.schedule(new ProcessTask(), 3, TimeUnit.SECONDS);

        // 循环执行任务，可设定第一次执行的时间，后续执行间隔
        threadPool.scheduleAtFixedRate(new ProcessTask(), 2, 5, TimeUnit.SECONDS);
    }
}
