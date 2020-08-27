package com.jensuper.prc.thread.lock;

/**
 * 类锁：线程同步执行，因为使用的是同一把锁
 * 静态对象锁：线程同步执行，使用的是同一把锁
 * this/普通对象锁/方法锁：线程异步执行，使用的是2个对象，所以是2把锁
 * @author jichao
 * @version V1.0
 * @description: synchronized 关键字
 * @date 2020/08/21
 */
public class SynchronizeTest {

    static Object tmpLock = new Object();

    public  void run() throws InterruptedException {
        synchronized (this) {
            System.out.println("开始执行");
            Thread.sleep(3000);
            System.out.println("执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                new SynchronizeTest().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            try {
                new SynchronizeTest().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
