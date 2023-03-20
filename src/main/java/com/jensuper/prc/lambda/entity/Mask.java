package com.jensuper.prc.lambda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 实体
 * </p>
 *
 * @date 2022/6/14 12:03
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mask {

    /**
     * 品牌
     */
    private String brand;
    /**
     * 类型
     */
    private String type;
}
