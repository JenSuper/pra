package com.jensuper.prc.design.builder;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description: 课程
 * @date 2019/08/19
 */
@Data
public class Course {

    private String title;
    private String content;
    private String price;

    public Course(CourseBuilder courseBuilder) {
        this.title = courseBuilder.getTitle();
        this.content = courseBuilder.getContent();
        this.price = courseBuilder.getPrice();
    }


}

