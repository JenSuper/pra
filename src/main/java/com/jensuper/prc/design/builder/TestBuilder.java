package com.jensuper.prc.design.builder;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/19
 */
public class TestBuilder {

    @Test
    public void test(){
        CourseBuilder courseBuilder = new CourseBuilder();
        Course course = courseBuilder.builderContent("内容").builderTitle("java").builderPrice("100.00").build();
        System.out.println(course);
    }

    @Test
    public void test2(){
        String sql = "select * from data_set join sys_role";
        String s = sql.replaceFirst("\\*", "123");
        System.out.println(s);

    }
}
