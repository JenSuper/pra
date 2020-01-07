package com.jensuper.prc.design.builder;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description:构建者
 * @date 2019/08/19
 */
@Data
public class CourseBuilder {

    private String title;
    private String content;
    private String price;

    public CourseBuilder builderTitle(String title) {
        this.title = title;
        return this;
    }

    public CourseBuilder builderContent(String content) {
        this.content = content;
        return this;
    }

    public CourseBuilder builderPrice(String price) {
        this.price = price;
        return this;
    }

    public Course build() {
        return new Course(this);
    }
}
