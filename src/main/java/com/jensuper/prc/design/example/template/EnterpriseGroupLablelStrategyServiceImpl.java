package com.jensuper.prc.design.example.template;

import com.jensuper.prc.design.example.Parameter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 企业模板
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 15:46
 * @since
 */
@Slf4j
public class EnterpriseGroupLablelStrategyServiceImpl extends AbstractGroupLabelJudgeTemplate {
    @Override
    public Boolean switchEnable() {
        return null;
    }

    @Override
    public Boolean singleRemote(Parameter parameter) {
        log.info("处理单个请求");
        return true;
    }

    @Override
    public Boolean batchRemote(Parameter parameter) {
        log.info("处理批量请求");
        return true;
    }

    @Override
    public String type() {
        return "enterprise";
    }
}
