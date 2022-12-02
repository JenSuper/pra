package com.jensuper.prc.design.builderKFC;

/**
 * <p>
 * 指挥者 Director：用来控制怎么构建
 * </p>
 *
 * @author jichao
 * @date 2022/5/30 16:26
 * @since
 */
public class MealWriter {

    private MealBuilder mealBuilder;

    public MealWriter(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal construst() {
        mealBuilder.builderDrink();
        mealBuilder.builderFood();
        return mealBuilder.getMeal();
    }
}
