package com.jensuper.prc.design.strategy;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 策略模式 https://chenwei.blog.csdn.net/article/details/70048478
 * @date 2019/08/21
 */
public class InitTest {

    /**
     * 策略模式
     * 1. 需要策略接口
     * 2. 不同的实例实现策略接口达到不同的实现方式
     * 3. 需要Context包装策略实现类
     */
    @Test
    public void test(){
//        TripStategy stategy = new TripStategy(new CarStrategy());
//        stategy.trip();
        TripStategy stategy = new TripStategy(new BusStrategy());
        stategy.trip();
    }
}
