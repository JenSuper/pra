package com.jensuper.prc.aop.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
@Slf4j
public class Record {

    private final String methodName;
    private final long begin;

    /**
     * 初始化
     * @param methodName
     */
    public Record(String methodName) {
        this.methodName = methodName;
        this.begin = System.currentTimeMillis();
    }

    /**
     * 打印方法消耗时间
     */
    public void print() {
        long end = System.currentTimeMillis();
        long time = end - this.begin;
        log.info(this.methodName+"消耗时间："+time);
    }
}
