package com.jensuper.prc.entity;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/02/17
 */
@Data
public class Person {

    private Integer id;
    private String name;

    public Person() {
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
