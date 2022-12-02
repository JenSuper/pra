package com.jensuper.prc.design.builderKFC;

/**
 * <p>
 * 测试入口
 * </p>
 *
 * @author jichao
 * @date 2022/5/30 16:29
 * @since
 */
public class InitTest {

    public static void main(String[] args) {
//        MealA mealA = new MealA();
        MealB mealB = new MealB();
        MealWriter mealWriter = new MealWriter(mealB);

        mealWriter.construst();
    }
}
