package com.jensuper.prc.bigone;

import com.googlecode.aviator.AviatorEvaluator;
import org.junit.Test;

/**
 * <p>
 * 计算
 * </p>
 *
 * @author jichao
 * @date 2022/8/4 18:04
 * @since
 */
public class Calculate {
    /**
     * AviatorEvaluator 计算
     */
    @Test
    public void test() {
        Object result = AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }
}
