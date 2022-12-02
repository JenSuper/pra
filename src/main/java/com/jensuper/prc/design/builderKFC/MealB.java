package com.jensuper.prc.design.builderKFC;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 具体建造者 ConcreteBuilder
 * </p>
 *
 * @author jichao
 * @date 2022/5/30 16:20
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MealB extends MealBuilder{

    @Override
    public void builderDrink() {
        System.out.println("两倍可乐");
    }

    @Override
    public void builderFood() {
        System.out.println("一份薯条🍟");
    }
}
