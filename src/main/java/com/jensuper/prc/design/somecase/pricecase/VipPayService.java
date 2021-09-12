package com.jensuper.prc.design.somecase.pricecase;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author jichao
 * @version V1.0
 * @description: vip 价格计算
 * @date 2020/11/02
 */
@Service
public class VipPayService implements PayService,InitializingBean {


    @Override
    public BigDecimal price(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal("0.9"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PayServiceFactory.registerService("Vip", new VipPayService());
    }
}
