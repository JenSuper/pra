package com.jensuper.prc.aop.monitor;

import com.jensuper.prc.aop.entity.Record;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/10/18
 */
@Slf4j
public class Monitor {

    //通过 ThreadLocal，保存与调用线程相关的性能监视信息
    private static ThreadLocal<Record> recordThreadLocal = new ThreadLocal<>();

    /**
     * 开始监控
     * @param methodName
     */
    public static void begin(String methodName) {
        log.info("--------------Monitor begin---------------");
        Record record = new Record(methodName);
        recordThreadLocal.set(record);
    }

    /**
     * 结束监控
     */
    public static void end() {
        log.info("--------------Monitor end---------------");
        Record record = recordThreadLocal.get();
        record.print();
    }
}
