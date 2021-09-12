package com.jensuper.prc.juc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jichao
 * @version V1.0
 * @description: 停止线程
 * @date 2020/08/30
 */
public class ShutDownThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ShutDownTask());
        }

        /**
         * 1. shutdown
         */
//        executorService.shutdown();

        /**
         * 2. isShutdown
         */
//        boolean shutdown = executorService.isShutdown();
//        System.out.println("isShutdown =》" + shutdown);

        /**
         * 3. isTerminated
         */
//        boolean terminated = executorService.isTerminated();
//        System.out.println("isTerminated =》" + terminated);
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        boolean terminatedEnd = executorService.isTerminated();
//        System.out.println("terminatedEnd =》" + terminatedEnd);


        /**
         * 4. awaitTermination
         */
//        try {
//            // 判断线程池中任务是否执行完毕
//            executorService.awaitTermination(10L, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * 3. isTerminated：立即停止正在执行的线程，并返回队列中未执行的任务
         */
//        List<Runnable> runnableList = executorService.shutdownNow();
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
