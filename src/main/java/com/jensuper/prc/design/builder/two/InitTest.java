package com.jensuper.prc.design.builder.two;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/07/07
 */
public class InitTest {
    public static void main(String[] args) {
        ItemParam itemParam = new ItemParam();
        itemParam.setName("user");
        itemParam.setColumn("id");
        String sql = Director.getSql("1", itemParam);
        System.out.println(sql);
    }
}
