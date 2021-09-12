package com.jensuper.prc.design.builder;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/19
 */
public class TestBuilder {

    /**
     * Product(产品类) :我们具体需要生成的类对象
     *
     * Builder(抽象建造者类)：为我们需要生成的类对象，构建不同的模块属性，即：公开构建产品类的属性，隐藏产品类的其他功能
     * ConcreteBuilder(具体建造者类)：实现我们要生成的类对象
     *
     * Director(导演类)：确定构建我们的类对象具体有哪些模块属性，在实际应用中可以不需要这个角色，直接通过client处理
     */

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
