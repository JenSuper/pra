package com.jensuper.prc.datastructure;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 二叉树
 * @date 2019/08/26
 */
public class BinaryTreeInit {

    @Test
    public void test(){
        // 构建二叉树(手动创建)
//        BinaryTreeNode treeNode = StaticCreatTree.createTree();
        // 构建二叉查找树
        int[] arr = {2, 3, 6, 5, 1};
        BinaryTreeRootNode rootNode = new BinaryTreeRootNode();
        for (int i : arr) {
            DynamicCreatTree.create(rootNode, i);
        }

        // 先序遍历
        System.out.println("先序遍历");
        firstFind(rootNode.getRootNode());
        // 中序遍历
//        System.out.println("中序遍历");
//        middleFind(treeNode);
        // 后序遍历
//        System.out.println("后序遍历");
//        finallyFind(treeNode);
    }

    /**
     * 先序遍历 先序(根->左->右)
     * @param treeNode
     */
    private void firstFind(BinaryTreeNode treeNode) {
        if (treeNode!=null) {
            System.out.println(treeNode.getValue());
            firstFind(treeNode.getLeftNode());
            firstFind(treeNode.getRigthNode());
        }
    }

    /**
     * 中序遍历 中序(左->根->右)
     * @param treeNode
     */
    private void middleFind(BinaryTreeNode treeNode) {
        if (treeNode!=null) {
            firstFind(treeNode.getLeftNode());
            System.out.println(treeNode.getValue());
            firstFind(treeNode.getRigthNode());
        }
    }

    /**
     * 后序遍历  后序(左->右->根)
     * @param treeNode
     */
    private void finallyFind(BinaryTreeNode treeNode) {
        if (treeNode!=null) {
            firstFind(treeNode.getLeftNode());
            firstFind(treeNode.getRigthNode());
            System.out.println(treeNode.getValue());
        }
    }


}
