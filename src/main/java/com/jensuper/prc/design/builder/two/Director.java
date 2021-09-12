package com.jensuper.prc.design.builder.two;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/07/07
 */
public class Director {
    public static String getSql(String type, ItemParam param) {
        ItemConcreteBuilder itemConcreteBuilder = new ItemConcreteBuilder();
        return itemConcreteBuilder.build(param);
    }
}
