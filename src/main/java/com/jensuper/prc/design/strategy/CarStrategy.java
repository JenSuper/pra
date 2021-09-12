package com.jensuper.prc.design.strategy;

/**
 * @author jichao
 * @version V1.0
 * @description: 汽车策略
 * @date 2019/08/21
 */
public class CarStrategy implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("this is CarStrategy");
    }
}
