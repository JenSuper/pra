package com.jensuper.prc.design.example.strategy;

import com.jensuper.prc.design.example.Parameter;

/**
 * <p>
 * 策略接口
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 13:57
 * @since
 */
public interface IGroupLabelStrategyService {

    Boolean process(Parameter parameter);
    String type();
}
