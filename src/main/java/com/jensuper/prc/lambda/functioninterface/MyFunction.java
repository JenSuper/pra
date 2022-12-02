package com.jensuper.prc.lambda.functioninterface;

/**
 * <p>
 *
 * </p>
 *
 * @author jichao
 * @date 2022/4/20 12:01
 * @since
 */
@FunctionalInterface
public interface MyFunction {

    void print(String str);

    static void sta() {
        System.out.println("sta");
    }
    default void def() {
        print("def");
    }
}
