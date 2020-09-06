package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: 缓存线程池
 * @date 2020/08/29
 */
public class CacheThreadPoolTest {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ProcessTask());
        }
        executorService.shutdown();
    }
}
