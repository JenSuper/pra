package com.jensuper.prc.lambda.method;

import cn.hutool.core.lang.func.VoidFunc0;
import com.jensuper.prc.lambda.entity.Mask;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>
 * 消费类
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 12:02
 * @since
 */
public class ConsumerTest {

    @Test
    public void supplier() {
        Supplier<Mask> supplier = () -> new Mask("red", "book");
        System.out.println(supplier.get());
    }

    @Test
    public void consumer() {
        Supplier<Mask> supplier = () -> new Mask("red", "book");
        Consumer<Mask> consumer = System.out::println;
        consumer.accept(supplier.get());
    }
}
