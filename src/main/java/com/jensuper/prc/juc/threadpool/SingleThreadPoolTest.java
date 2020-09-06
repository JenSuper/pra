package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: SingleThreadPoolTest 单例线程池
 * @date 2020/08/29
 */
public class SingleThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ProcessTask());
        }
        executorService.shutdown();
    }
}
