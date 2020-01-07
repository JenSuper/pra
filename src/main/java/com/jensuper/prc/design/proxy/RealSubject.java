package com.jensuper.prc.design.proxy;

/**
 * @author jichao
 * @version V1.0
 * @description: 真实对象
 * @date 2019/08/21
 */
public class RealSubject implements Subject {

    @Override
    public void print() {
        System.out.println("this is realSubject");
    }
}
