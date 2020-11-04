package com.jensuper.prc.juc.threadlocal;

import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jichao
 * @version V1.0
 * @description: 使用ThreadLocal 方式  利用ThreadLocal，给每个线程分配自己的dateFormat对象，保证了线程安全，高效利用内存
 * @date 2020/09/16
 */
public class ThreadLocalNormalUse3 {


    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("format date");
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executorService.execute(()->{
                String time = new ThreadLocalNormalUse3().convertDate(finalI);
                System.out.println(time);
            });
        }
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                stopWatch.stop();
                System.out.println(stopWatch.prettyPrint());
                break;
            }
        }
    }

    /**
     * 日期格式化
     * @param time
     * @return
     */
    private String convertDate(int time) {
        Date date = new Date(time * 1000);
        return SimpleDateFormatSafe.simpleDateFormat2.get().format(date);
    }

}

class SimpleDateFormatSafe{

    public static ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /**
     * lambda 方式
     */
    public static ThreadLocal<SimpleDateFormat> simpleDateFormat2 = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
