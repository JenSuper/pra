package com.jensuper.prc.basic;


import com.jensuper.prc.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/04/28
 */
public class ReflectSample {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.jensuper.prc.entity.User");
        User user = (User) aClass.newInstance();

        // 设置公共属性及调用公共方法
        user.setId(1L);
        user.setName("小明");
        System.out.println("name => " + user.getName());
        user.printPublic();

        // 获取公共方法
        Method printPublic = aClass.getMethod("printPublic");
        printPublic.invoke(user);

        // 修改私有属性
        Field filed = aClass.getDeclaredField("name");
        filed.setAccessible(true);
        filed.set(user, "百丽");

        // 执行私有方法
        Method printPrivate = aClass.getDeclaredMethod("printPrivate");
        printPrivate.setAccessible(true);
        printPrivate.invoke(user);
    }
}
