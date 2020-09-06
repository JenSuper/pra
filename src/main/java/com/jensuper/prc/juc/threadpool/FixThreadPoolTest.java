package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: FixThreadPool : 指定线程数量
 * @date 2020/08/29
 */
public class FixThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ProcessTask());
        }
        executorService.shutdown();
    }
}

class ProcessTask implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" => run....");
    }
}
