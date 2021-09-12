package com.jensuper.prc.demo;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/12/08
 */
public interface TestService {
    default String get() {
        return "1";
    }
    default String get2() {
        return "1";
    }
}
