package com.jensuper.prc.design.factory.factory3.creat;


import com.jensuper.prc.design.factory.factory3.factroy.SimpleFactory;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/16
 */
public class CreateFactory {

    public static SimpleFactory getFactory(Class clazz) {
        try {
            try {
                Class<?> aClass = Class.forName(clazz.getName());
                return (SimpleFactory) aClass.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            return (SimpleFactory) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
