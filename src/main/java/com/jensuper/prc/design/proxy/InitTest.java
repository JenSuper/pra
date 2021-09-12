package com.jensuper.prc.design.proxy;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 代理模式
 * @date 2019/08/21
 */
public class InitTest {

    @Test
    public void test(){
        ProxySubject proxySubject = new ProxySubject();
        proxySubject.print();
    }

}
