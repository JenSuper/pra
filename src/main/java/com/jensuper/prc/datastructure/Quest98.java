package com.jensuper.prc.datastructure;

/**
 * @author jichao
 * @version V1.0
 * @description: 验证二叉搜索树
 * @date 2021/06/09
 */
public class Quest98 {

    private long pre = Long.MIN_VALUE;

    public static long min = Long.MIN_VALUE;
    public static long max = Long.MAX_VALUE;


    /**
     * 中序遍历
     * @param root
     * @return
     */
    public boolean isValidBST(BinaryTreeNode root) {

        if (root == null) {
            return true;
        }
        // 左节点
        if (!isValidBST(root.getLeftNode())) {
            return false;
        }
        // 根节点
        if (root.getValue() <= pre) {
            return false;
        }
        // 右节点
        pre = root.getValue();
        return isValidBST(root.getRigthNode());
    }

    /**
     * 递归遍历
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(BinaryTreeNode root,long min,long max) {
        if (root == null) {
            return true;
        }
        // 判断当前节点，如果小于小值，或者大于大值，则说明非有序
        if (root.getValue() <= min || root.getValue() >= max) {
            return false;
        }
        return isValidBST2(root.getLeftNode(), min, root.getValue()) && isValidBST2(root.getRigthNode(), root.getValue(), max);
    }

    public static void main(String[] args) {
        BinaryTreeNode tree = StaticCreatTree.createTree();
        Quest98 quest98 = new Quest98();
        System.out.println(quest98.isValidBST(tree));
//        System.out.println(quest98.isValidBST2(tree, min, max));
    }
}
