package com.jensuper.prc.design.factory.factory1.init;

import com.jensuper.prc.design.factory.factory1.factoryimpl.Compute;
import com.jensuper.prc.design.factory.factory1.factoryimpl.Keyboard;
import com.jensuper.prc.design.factory.factory1.factroy.SimpleFactory;
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
        // 第一种方式：通过创建实现类获取不同的对象
        SimpleFactory factory = new Keyboard();
        factory.print();
        SimpleFactory factory1 = new Compute();
        factory1.print();
    }
}
