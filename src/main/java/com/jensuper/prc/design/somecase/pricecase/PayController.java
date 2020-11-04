package com.jensuper.prc.design.somecase.pricecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/11/02
 */
@RestController
@RequestMapping("/pricecase")
public class PayController {


    @GetMapping("/svip/{price}")
    public BigDecimal paySvipService(@PathVariable(value = "price")BigDecimal price) {
        PayService svip = PayServiceFactory.getType("Svip");
        return svip.price(price);
    }

    @GetMapping("/vip/{price}")
    public BigDecimal payVipService(@PathVariable(value = "price")BigDecimal price) {
        PayService vip = PayServiceFactory.getType("Vip");
        return vip.price(price);
    }

    @GetMapping("/normal/{price}")
    public BigDecimal payNormalService(@PathVariable(value = "price")BigDecimal price) {
        PayService normal = PayServiceFactory.getType("Normal");
        return normal.price(price);
    }
}
