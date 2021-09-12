package com.jensuper.prc.juc.threadlocal;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jichao
 * @version V1.0
 * @description: 空指针情况： get 方法返回值类型必须使用包装类，否则会存在拆箱和装修的情况
 * @date 2020/09/16
 */
public class ThreadLocalNormalUseNPE {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void set() {
        threadLocal.set(123L);
    }

    public Long get() {
        return threadLocal.get();
    }

    public static void main1(String[] args) {
        ThreadLocalNormalUseNPE threadLocalNormalUseNPE = new ThreadLocalNormalUseNPE();
        System.out.println(threadLocalNormalUseNPE.get());
        new Thread() {
            @Override
            public void run() {
                threadLocalNormalUseNPE.set();
                System.out.println(threadLocalNormalUseNPE.get());
            }
        }.start();

    }
}

