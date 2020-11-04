package com.jensuper.prc.design.somecase.pricecase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jichao
 * @version V1.0
 * @description: 支付计算工厂类：工厂模式
 * @date 2020/11/02
 */
public class PayServiceFactory {

    public static Map<String, PayService> serviceMap = new ConcurrentHashMap<>();


    /**
     * 根据类型获取计算折扣方式
     * @param type
     * @return
     */
    public static PayService getType(String type) {
        return serviceMap.get(type);
    }

    /**
     * 注册计算折扣服务
     * @param type
     * @param payService
     */
    public static void registerService(String type,PayService payService){
        serviceMap.put(type, payService);
    }

}
