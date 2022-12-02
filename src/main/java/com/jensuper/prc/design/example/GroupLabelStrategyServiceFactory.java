package com.jensuper.prc.design.example;

import com.jensuper.prc.design.example.Parameter;
import com.jensuper.prc.design.example.strategy.IGroupLabelStrategyService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 全局工厂
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 14:02
 * @since
 */
@Component
public class GroupLabelStrategyServiceFactory implements ApplicationContextAware {
    /**
     * 类型与对应的实现
     */
    private Map<String, IGroupLabelStrategyService> handleMap = new ConcurrentHashMap<>();
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 将所有实现类放入map 中
        Map<String, IGroupLabelStrategyService> beansOfType = applicationContext.getBeansOfType(IGroupLabelStrategyService.class);
        beansOfType.values().forEach(service -> handleMap.put(service.type(), service));
    }

    /**
     * 执行实现类方法
     * @param parameter
     * @return
     */
    public Boolean process(Parameter parameter) {
        // 根据不同的类型调用具体的实现类
        IGroupLabelStrategyService iGroupLabelStrategyService = handleMap.get(parameter.getType());
        if (iGroupLabelStrategyService != null) {
            return iGroupLabelStrategyService.process(parameter);
        }
        return false;
    }
}
