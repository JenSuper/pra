package com.jensuper.prc.design.example.template;

import com.jensuper.prc.design.example.Parameter;
import com.jensuper.prc.design.example.strategy.IGroupLabelStrategyService;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 模板父类方法
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 14:23
 * @since
 */
@Slf4j
public abstract class AbstractGroupLabelJudgeTemplate implements IGroupLabelStrategyService {

    @Override
    public Boolean process(Parameter parameter) {
        if (!switchEnable()) {
            return false;
        }
        if (parameter.getReqNum() == 1) {
            // 单个处理
            return singleRemote(parameter);
        }
        if (parameter.getReqNum() > 1) {
            // 处理批量
            return batchRemote(parameter);
        }
        return false;
    }

    public abstract Boolean switchEnable();

    public abstract Boolean singleRemote(Parameter parameter);

    public abstract Boolean batchRemote(Parameter parameter);
}
