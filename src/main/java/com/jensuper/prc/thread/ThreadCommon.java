package com.jensuper.prc.thread;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/20
 */
public class ThreadCommon implements Runnable {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    private Integer addVar() {
        int i = threadLocal.get() + 1;
        threadLocal.set(i);
        return threadLocal.get();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "currentInteger==> " + addVar());
        }
    }
}
