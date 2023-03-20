package com.jensuper.prc.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jichao
 * @version V1.0
 * @description: 演示：锁被中断的情况
 * @date 2020/09/26
 */
public class LockInterruptibly implements Runnable {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        LockInterruptibly lockInterruptibly = new LockInterruptibly();
        LockInterruptibly lockInterruptibly2 = new LockInterruptibly();

        Thread thread = new Thread(lockInterruptibly);
        Thread thread1 = new Thread(lockInterruptibly2);

        thread.start();
        thread1.start();

        try {
            // 等待
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "睡眠期间发生了异常");
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "获取锁期间发生了异常");
        }
    }
}
