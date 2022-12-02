package com.jensuper.prc.design.example.strategy;

import com.jensuper.prc.design.example.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业类型
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 13:58
 * @since
 */
@Service
@Slf4j
public class EnterpriseGroupLablelStrategyServiceImpl implements IGroupLabelStrategyService{

    private Boolean switchEnable = true;

    @Override
    public Boolean process(Parameter parameter) {
        if (!switchEnable) {
            return false;
        }
        if (parameter.getReqNum() == 1) {
            // 单个处理
            log.info("处理单个请求");
            return true;
        }
        if (parameter.getReqNum() > 1) {
            // 处理批量
            log.info(("批量"));
            return true;
        }

        return false;
    }

    @Override
    public String type() {
        return "enterprise";
    }
}
