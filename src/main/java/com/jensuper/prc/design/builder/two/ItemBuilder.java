package com.jensuper.prc.design.builder.two;

/**
 * @author jichao
 * @version V1.0
 * @description: 抽象建造者
 * @date 2021/07/07
 */
public abstract class ItemBuilder<T> {

    public abstract void buildCreate();
    public abstract void buildColumn();

    public abstract String build(T t);

    public void print() {
        System.out.println(111);
    }
}
