package com.jensuper.prc.lambda.functioninterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 测试
 * </p>
 *
 * @author jichao
 * @date 2022/4/20 14:32
 * @since
 */
public class InitTest {
    public static void main(String[] args) {
        MyFunction.sta();
        final Pattern compile = Pattern.compile("([a-zA-Z]+)");
        fun("( (convictionPrice / currentPrice) * conviction + (bearPrice / currentPrice) * (1 - conviction) ) * proportion * 100", (a)->{
            Matcher matcher = compile.matcher(a);
            while (matcher.find()) {
                System.out.println(matcher.group(1));
            }
        });
    }

    private static void fun(String str,MyFunction function) {
        function.print(str);
        function.def();
    }
}
