package com.jensuper.prc.design.singleton;

/**
 * @author jichao
 * @version V1.0
 * @description: 单例模式: https://chenwei.blog.csdn.net/article/details/69382150
 * @date 2021/07/07
 */
public class InitTest {

    public static void main(String[] args) {
        Single single = Single.single();
        Single single2 = Single.single();
    }
}
