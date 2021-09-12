package com.jensuper.prc.design.proxy;

/**
 * @author jichao
 * @version V1.0
 * @description: 代理类
 * @date 2019/08/21
 */
public class ProxySubject implements Subject {

    private Subject subject;

    @Override
    public void print() {
        if (subject == null) {
            this.subject = new RealSubject();
        }
        subject.print();
        System.out.println("this is proxySubject");
    }
}
