package com.jensuper.prc.juc.redission;

import lombok.extern.slf4j.Slf4j;
import org.python.modules._threading.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * redission
 * </p>
 *
 * @author jichao
 * @date 2022/9/23 10:18
 */
@Slf4j
public class RedissionLock {
//    @Autowired
//    private RedissonClient redissonClient;
//
//    @GetMapping(value = "/test")
//    public void saveNotice() {
//        ExecutorService service = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < 10; i++) {
//            service.execute(()->{
//                lockTest();
//            });
//        }
//        service.shutdown();
//    }

//
//    @GetMapping(value = "/test2")
//    public void saveNotice2() {
//        ExecutorService service = Executors.newFixedThreadPool(10);
//
//        for (int i = 0; i < 10; i++) {
//            service.execute(()->{
//                log.info("当前线程=>" + Thread.currentThread().getName());
//            });
//        }
//        service.shutdown();
//    }
//
//    private void lockTest() {
//        log.info("当前线程=>" + Thread.currentThread().getName());
//        RLock lock = redissonClient.getLock("test_local");
//        try {
//            lock.lock();
//            log.info("获取到锁了,当前线程=>" + Thread.currentThread().getName());
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            log.info("释放锁=>" + Thread.currentThread().getName());
//            lock.unlock();
//        }
//    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now().minusMonths(1L));
    }
}
