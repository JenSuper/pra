package com.jensuper.prc.design.composite;

/**
 * @author jichao
 * @version V1.0
 * @description: 树叶构建
 * @date 2019/08/21
 */
public class PersonLeaf extends PersonNode{

    public PersonLeaf(String name, String sex, int age) {
        super(name, sex, age);
    }
}
