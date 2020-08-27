package com.jensuper.prc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 桑基图
 * @date 2020/02/20
 */
@Data
@Accessors(chain = true)
public class SankeyChartData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 子节点
     */
    private List<SankeyChartData> children;
}
