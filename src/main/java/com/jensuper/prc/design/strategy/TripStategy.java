package com.jensuper.prc.design.strategy;

/**
 * @author jichao
 * @version V1.0
 * @description: 出行方式
 * @date 2019/08/21
 */
public class TripStategy {

    /**
     * 构造方法注入对应的策略模式
     */
    private Strategy strategy;

    public TripStategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 当前策略模式
     */
    public void trip() {
        strategy.doSomething();
    }
}
