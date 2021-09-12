package com.jensuper.prc.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jichao
 * @version V1.0
 * @description: 代码演示：必须解锁
 * @date 2020/09/26
 */
public class MustUnLock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
        System.out.println("end");
    }
}
