package com.jensuper.prc.design.strategy;

/**
 * @author jichao
 * @version V1.0
 * @description: bus 策略
 * @date 2019/08/21
 */
public class BusStrategy implements Strategy{

    @Override
    public void doSomething() {
        System.out.println("this is BusStrategy");
    }
}
