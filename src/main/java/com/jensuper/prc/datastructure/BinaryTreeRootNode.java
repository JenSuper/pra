package com.jensuper.prc.datastructure;

import lombok.Data;

/**
 * @author jichao
 * @version V1.0
 * @description: 树根
 * @date 2019/08/26
 */
@Data
public class BinaryTreeRootNode {

    private BinaryTreeNode rootNode;

    public BinaryTreeRootNode() {
    }

    public BinaryTreeRootNode(BinaryTreeNode rootNode) {
        this.rootNode = rootNode;
    }
}
