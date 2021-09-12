package com.jensuper.prc.design.composite;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description: 抽象构件抽象族员类
 * @date 2019/08/21
 */
@Data
public abstract class PersonNode {
    /**
     * 人名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年龄
     */
    private int age;

    public PersonNode(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getPersonNode() {
        return "name=>" + this.name + "sex=>" + this.sex + "age=>" + this.age;
    }
}
