package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: FixThreadPool OOM   >修改启动配置：-Xmx8m -Xms8m
 * @date 2020/08/29
 */
public class FixThreadPoolOOMTest {

    public static void main(String[] args) {
        Executors.newWorkStealingPool();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new SubTask());
        }
        executorService.shutdown();
    }
}

class SubTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" => run....");
    }
}
