package com.jensuper.prc.design.factory.factory3.init;

import com.jensuper.prc.design.factory.factory3.factoryimpl.Keyboard;
import com.jensuper.prc.design.factory.factory3.factroy.SimpleFactory;
import com.jensuper.prc.design.factory.factory3.creat.CreateFactory;
import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/16
 */
public class TestInit {

    @Test
    public void test(){
        // 第三种方式：通过类创建对象
        SimpleFactory compute = CreateFactory.getFactory(Keyboard.class);
        compute.print();
    }
}
