package com.jensuper.prc.aop.cglibproxy;

import com.jensuper.prc.aop.monitor.Monitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
public class CglibHandler implements MethodInterceptor {

    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Monitor.begin(method.getName());
        Object obj = method.invoke(this.target, objects);
        Monitor.end();
        return obj;
    }

    /**
     * 获取动态代理
     * @param targetObject
     * @return
     */
    public Object getCglibHandler(Object targetObject) {
        this.target = targetObject;
        Enhancer enhancer = new Enhancer();
        // 设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(CglibHandler.this);
        Object ret = enhancer.create();
        return ret;
    }
}
