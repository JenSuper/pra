package com.jensuper.prc.design.example.template;

import com.jensuper.prc.design.example.Parameter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 市场
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 16:01
 * @since
 */
@Slf4j
public class MarketGroupLablelStrategyServiceImpl extends AbstractGroupLabelJudgeTemplate {
    @Override
    public Boolean switchEnable() {
        return true;
    }

    @Override
    public Boolean singleRemote(Parameter parameter) {
        log.info("处理单个请求");
        return true;
    }

    @Override
    public Boolean batchRemote(Parameter parameter) {
        log.info(("批量"));
        return true;
    }

    @Override
    public String type() {
        return "market";
    }
}
