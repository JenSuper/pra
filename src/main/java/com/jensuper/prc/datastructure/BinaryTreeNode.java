package com.jensuper.prc.datastructure;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/26
 */
@Data
public class BinaryTreeNode {

    /**
     * 值
     */
    private Integer value;

    /**
     * 左子树
     */
    private BinaryTreeNode leftNode;

    /**
     * 右子树
     */
    private BinaryTreeNode rigthNode;

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }
}
