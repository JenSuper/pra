package com.jensuper.prc.design.builderKFC;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 套餐A
 * </p>
 *
 * @author jichao
 * @date 2022/5/30 16:17
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MealA extends MealBuilder{

    @Override
    public void builderDrink() {
        System.out.println("两杯果汁🧃");
    }

    @Override
    public void builderFood() {
        System.out.println("一份汉堡🍔");
    }
}
