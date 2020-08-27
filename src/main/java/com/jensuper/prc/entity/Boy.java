package com.jensuper.prc.entity;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/02/18
 */
@Data
public class Boy {
    private Integer id;
    private String name;

    public Boy(String name) {
        this.name = name;
    }
}
