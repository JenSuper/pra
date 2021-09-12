package com.jensuper.prc.design.factory.factory2.creat;

import com.jensuper.prc.design.factory.factory1.factoryimpl.Compute;
import com.jensuper.prc.design.factory.factory1.factoryimpl.Keyboard;
import com.jensuper.prc.design.factory.factory1.factroy.SimpleFactory;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/16
 */
public class CreateFactory {

    public static SimpleFactory getFactory(String param) {
        switch (param) {
            case "compute":
                return new Compute();
            case "keyborad":
                return new Keyboard();
            default:
                return null;
        }
    }
}
