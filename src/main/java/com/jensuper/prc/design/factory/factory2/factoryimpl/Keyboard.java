package com.jensuper.prc.design.factory.factory2.factoryimpl;

import com.jensuper.prc.design.factory.factory2.factroy.SimpleFactory;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/16
 */
public class Keyboard implements SimpleFactory {

    @Override
    public void print() {
        System.out.println("keyboard");
    }
}
