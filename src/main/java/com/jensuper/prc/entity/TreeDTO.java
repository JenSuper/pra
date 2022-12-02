package com.jensuper.prc.entity;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 树结构
 * </p>
 *
 * @author jichao
 * @date 2021/10/21 16:30
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Integer id;
    private String name;
    private Integer pid;
    private Integer level;
    private List<TreeDTO> children;

    public List<TreeDTO> init() {
        return Lists.newArrayList(
                new TreeDTO(203, "食品酒水", 0, 1, null),
                new TreeDTO(209, "医疗健康", 0, 1, null),
                new TreeDTO(206, "3C数码", 0, 1, null),
                new TreeDTO(20311, "饮料", 203, 2, null),
                new TreeDTO(20308, "茶", 203, 2, null),
                new TreeDTO(20301, "休闲食品", 203, 2, null),
                new TreeDTO(20906, "营养成分", 209, 2, null),
                new TreeDTO(20603, "办公文仪", 206, 2, null),
                new TreeDTO(2030107, "蜜饯果干", 20301, 3, null),
                new TreeDTO(2030608, "鲜肉", 20306, 3, null),
                new TreeDTO(2030604, "海鲜水产", 20306, 3, null),
                new TreeDTO(2060315, "纸类", 20603, 3, null)
        );
    }
}
