package com.jensuper.prc.design.somecase.pricecase;

import java.math.BigDecimal;

/**
 * @author jichao
 * @version V1.0
 * @description: 计算方式：策略模式
 * @date 2020/11/02
 */
public interface PayService {

    /**
     * 计算价格
     * @param orderPrice
     * @return
     */
    BigDecimal price(BigDecimal orderPrice);
}
