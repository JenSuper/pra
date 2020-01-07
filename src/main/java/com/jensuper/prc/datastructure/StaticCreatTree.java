package com.jensuper.prc.datastructure;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2019/08/26
 */
public class StaticCreatTree {
    /**
     * 构建二叉树
     *
     */
    public static BinaryTreeNode createTree() {

        //      10
        //  8        20
        //        15     30
        // 根节点
        BinaryTreeNode root = new BinaryTreeNode(10);

        // 左子树
        BinaryTreeNode leftNode = new BinaryTreeNode(8);

        // 右子树
        BinaryTreeNode rigthNode = new BinaryTreeNode(20);
        //右子树左节点
        BinaryTreeNode rigthNodeChildLeft = new BinaryTreeNode(15);
        //右子树右节点
        BinaryTreeNode rigthNodeChildRigth = new BinaryTreeNode(30);

        // 创建二叉树
        root.setLeftNode(leftNode);

        rigthNode.setLeftNode(rigthNodeChildLeft);
        rigthNode.setRigthNode(rigthNodeChildRigth);
        root.setRigthNode(rigthNode);

        return root;
    }
}
