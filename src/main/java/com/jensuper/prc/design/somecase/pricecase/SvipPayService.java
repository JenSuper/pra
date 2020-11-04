package com.jensuper.prc.design.somecase.pricecase;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author jichao
 * @version V1.0
 * @description: svip 价格计算
 * @date 2020/11/02
 */
@Service
public class SvipPayService implements PayService,InitializingBean {


    @Override
    public BigDecimal price(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal("0.7"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        PayServiceFactory.registerService("Svip", new SvipPayService());
    }
}
