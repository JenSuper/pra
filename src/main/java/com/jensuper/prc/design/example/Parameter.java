package com.jensuper.prc.design.example;

import lombok.Data;

/**
 * <p>
 * 参数
 * </p>
 *
 * @author jichao
 * @date 2022/6/14 13:55
 * @since
 */
@Data
public class Parameter {
    private int pageSize;
    private int pageNo;
    private int reqNum;
    private String type;

}
