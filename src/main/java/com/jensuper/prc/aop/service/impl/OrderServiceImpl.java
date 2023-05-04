package com.jensuper.prc.aop.service.impl;

import com.jensuper.prc.aop.monitor.Monitor;
import com.jensuper.prc.aop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Override
    public void add() {
        // 开启监控
        Monitor.begin("OrderServiceImpl add()");
        log.info("-----------add-----------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 结束监控
        Monitor.end();
    }

    @Override
    public void addFour() {
        StopWatch stopWatch = new StopWatch();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
