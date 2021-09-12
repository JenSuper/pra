package com.jensuper.prc.design.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jichao
 * @version V1.0
 * @description: 单例模式:双重校验锁 https://chenwei.blog.csdn.net/article/details/69382150
 * @date 2021/07/07
 */
@Slf4j
public class Single {

    public Single() {
    }

    private volatile static Single single;

    public static Single single() {
        if (single == null) {
            synchronized (Single.class) {
                if (single == null) {
                    log.info("init...");
                    single = new Single();
                }
            }
        }
        return single;
    }
}
