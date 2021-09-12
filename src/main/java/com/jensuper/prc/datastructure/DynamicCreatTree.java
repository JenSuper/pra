package com.jensuper.prc.datastructure;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/26
 */
public class DynamicCreatTree {


    /**
     * 动态创建二叉查找树
     * 1. 第一次初始化树根
     * 2. 获取树根
     * 3. 比较value与树根大小
     * 4. 比树根小放到左边，比树根大放在左边
     *
     * @param rootNode
     * @param value
     */
    public static void create(BinaryTreeRootNode rootNode, int value) {
        // 第一次创建
        if (rootNode.getRootNode() == null) {
            rootNode.setRootNode(new BinaryTreeNode(value));
        } else {
            // 获取当前根节点
            BinaryTreeNode currentRootNode = rootNode.getRootNode();

            while (currentRootNode != null) {
                // 根节点value
                Integer rootNodeValue = currentRootNode.getValue();
                // 比根节点小，放左边
                if (value < rootNodeValue) {
                    // 判断左侧是否有node
                    if (currentRootNode.getLeftNode() == null) {
                        currentRootNode.setLeftNode(new BinaryTreeNode(value));
                        return;
                    }
                    currentRootNode = currentRootNode.getLeftNode();
                }
                // 比根节点大，放右边
                if (value > rootNodeValue) {
                    // 判断右测是否有node
                    if (currentRootNode.getRigthNode()==null) {
                        currentRootNode.setRigthNode(new BinaryTreeNode(value));
                        return;
                    }
                    currentRootNode = currentRootNode.getRigthNode();
                }
            }
        }
    }
}
