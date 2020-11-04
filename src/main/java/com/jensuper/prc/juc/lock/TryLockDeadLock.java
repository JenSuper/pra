package com.jensuper.prc.juc.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jichao
 * @version V1.0
 * @description: 演示使用 TryLock 避免死锁
 * @date 2020/09/26
 */
public class TryLockDeadLock implements Runnable {

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock tryLockDeadLock = new TryLockDeadLock();
        TryLockDeadLock tryLockDeadLock2 = new TryLockDeadLock();
        tryLockDeadLock.flag = 1;
        tryLockDeadLock2.flag = 0;
        new Thread(tryLockDeadLock).start();
        new Thread(tryLockDeadLock2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

            if (flag == 1) {
                try {
                    // 获取锁1
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        // 获取到锁
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到Lock1");
                            Thread.sleep(new Random().nextInt(1000));
                            // 获取锁2
                            if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "获取到Lock2");
                                    Thread.sleep(new Random().nextInt(1000));
                                    System.out.println(Thread.currentThread().getName() + "获取到两把锁");
                                    break;
                                } finally {
                                    lock2.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                // 未获取到锁,重试
                                System.out.println(Thread.currentThread().getName() + "未获取到Lock2，重试");
                            }
                        } finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        // 未获取到锁,重试
                        System.out.println(Thread.currentThread().getName() + "未获取到Lock1,重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0) {
                try {
                    // 获取锁1
                    if (lock2.tryLock(800, TimeUnit.MILLISECONDS)) {
                        // 获取到锁
                        try {
                            System.out.println(Thread.currentThread().getName() + "获取到Lock2");
                            Thread.sleep(new Random().nextInt(1000));
                            // 获取锁2
                            if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + "获取到Lock1");
                                    Thread.sleep(new Random().nextInt(1000));
                                    System.out.println(Thread.currentThread().getName() + "获取到两把锁");
                                    break;
                                } finally {
                                    lock1.unlock();
                                    Thread.sleep(new Random().nextInt(1000));
                                }
                            } else {
                                // 未获取到锁,重试
                                System.out.println(Thread.currentThread().getName() + "未获取到Lock2，重试");
                            }
                        } finally {
                            lock2.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        // 未获取到锁,重试
                        System.out.println(Thread.currentThread().getName() + "未获取到Lock1，重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
