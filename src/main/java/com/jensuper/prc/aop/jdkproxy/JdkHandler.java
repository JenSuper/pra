package com.jensuper.prc.aop.jdkproxy;

import com.jensuper.prc.aop.monitor.Monitor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jichao
 * @version V1.0
 * @description: jdk动态代理
 * @date 2019/10/18
 */
@Slf4j
public class JdkHandler implements InvocationHandler {

    /**
     * 目标类
     */
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke");
        Monitor.begin(method.getName());
        // 调用方法
        Object obj = method.invoke(target, args);
        Monitor.end();
        return obj;
    }

    /**
     * 定义获取代理对象的方法
     * @param targetObject
     * @return
     */
    public Object getJDKProxy(Object targetObject) {
        log.info("getJDKProxy");
        this.target = targetObject;
        // JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        Object ret = Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), JdkHandler.this);
        return ret;
    }
}
