package com.jensuper.prc.design.builderKFC;

/**
 * <p>
 * 抽象建造者 Builder
 * </p>
 *
 * @author jichao
 * @date 2022/5/30 16:22
 * @since
 */
public abstract class MealBuilder {

    private Meal meal = new Meal();

    public abstract void builderDrink();
    public abstract void builderFood();

    public Meal getMeal() {
        return meal;
    }

}
