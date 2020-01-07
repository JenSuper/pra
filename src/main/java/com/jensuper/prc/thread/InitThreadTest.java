package com.jensuper.prc.thread;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/20
 */
public class InitThreadTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadCommon());
        Thread thread2 = new Thread(new ThreadCommon());
        Thread thread3 = new Thread(new ThreadCommon());
        thread.start();
        thread2.start();
        thread3.start();

    }
}
