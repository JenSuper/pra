package com.jensuper.prc.aop.init;

import com.jensuper.prc.aop.cglibproxy.CglibHandler;
import com.jensuper.prc.aop.jdkproxy.JdkHandler;
import com.jensuper.prc.aop.service.OrderService;
import com.jensuper.prc.aop.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
@Slf4j
public class Init {

    private OrderService orderService;

    private void set() {
        orderService = new OrderServiceImpl();
    }

    /**
     * 在执行add方法时，进行时间监控
     * 1. 执行前记录开始时间
     * 2. 执行后计算出消耗时间
     */
    @Test
    public void test(){
        this.set();
        orderService.add();
        orderService.addFour();
    }

    /**
     * jdk动态代理
     */
    @Test
    public void testJdk(){
        JdkHandler jdkHandler = new JdkHandler();
        OrderService service = (OrderService)jdkHandler.getJDKProxy(new OrderServiceImpl());
        service.add();
    }

    /**
     * cglib动态代理
     */
    @Test
    public void testCglib(){
        CglibHandler cglibHandler = new CglibHandler();
        OrderService service = (OrderService)cglibHandler.getCglibHandler(new OrderServiceImpl());
        service.add();
    }
}
