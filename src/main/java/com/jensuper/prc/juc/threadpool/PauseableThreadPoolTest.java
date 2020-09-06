package com.jensuper.prc.juc.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jichao
 * @version V1.0
 * @description: 在线程池中，线程执行前后可以添加函数处理
 * @date 2020/08/30
 */
public class PauseableThreadPoolTest extends ThreadPoolExecutor {

    /**
     * 锁
     */
    private ReentrantLock lock = new ReentrantLock();

    private Condition unPaused = lock.newCondition();

    /**
     * 暂停状态
     */
    private boolean isPaused;


    public PauseableThreadPoolTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        // 执行之前需要判断暂停状态是否为 true
        lock.lock();
        try {
            while (isPaused) {
                unPaused.await();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 设置暂停方法
     */
    private void pause() {
        lock.lock();
        try {
            isPaused = true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 取消暂停方法
     */
    private void resume() {
        lock.lock();
        try {
            isPaused = false;
            unPaused.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        PauseableThreadPoolTest threadPoolTest =
                new PauseableThreadPoolTest(10, 20, 6L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " => 执行方法");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 10000; i++) {
            threadPoolTest.execute(runnable);
        }

        // 等待一定时间，设置线程状态为暂停
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolTest.pause();
        System.out.println(Thread.currentThread().getName()+" => 线程被暂停了");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolTest.resume();
        System.out.println(Thread.currentThread().getName()+" => 线程被唤醒了");
    }
}
