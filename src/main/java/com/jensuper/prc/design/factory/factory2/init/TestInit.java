package com.jensuper.prc.design.factory.factory2.init;

import com.jensuper.prc.design.factory.factory1.factroy.SimpleFactory;
import com.jensuper.prc.design.factory.factory2.creat.CreateFactory;
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
        // 第二种方式：通过参数获取不同的对象
        SimpleFactory compute = CreateFactory.getFactory("compute");
        compute.print();
    }
}
