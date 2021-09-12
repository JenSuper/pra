package com.jensuper.prc.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author jichao
 * @version V1.0
 * @description: 102. 二叉树的层序遍历 ==> 广度遍历O(n) 深度遍历 0(N)  https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @date 2021/06/26
 */
public class Quest102 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     */
    class Solution {
        /**
         * BFS：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
         *
         *  使用队列
         *
         *  while 循环的每一轮中，都是将当前层的所有结点出队列，再将下一层的所有结点入队列
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            // 记录每一层的节点，先从根节点开始
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            // 遍历队列中节点
            while (!queue.isEmpty()) {
                // 当前层节点值集合
                List<Integer> currentValue = new ArrayList<>();

                // 输出当前层，找到当前层的值，将当前层的左右子节点放入队列
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode currentNode = queue.poll();
                    currentValue.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.add(currentNode.right);
                    }
                }
                result.add(currentValue);
            }
            return result;
        }

        /**
         * 深度遍历DFS：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/die-dai-di-gui-duo-tu-yan-shi-102er-cha-shu-de-cen/
         *
         * 时间复杂度：O(N)O(N)
         * 空间复杂度：O(h)O(h)，h 是树的高度
         *
         * 每次递归的时候都需要带一个 index(表示当前的层数),如果不存在就创建一个空的容器
         * 将每个节点放入index对应层的容器中
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder2(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            // 从第一层开始
            List<List<Integer>> res = new ArrayList<>();
            dfs(1, root, res);
            return res;
        }

        private void dfs(int index, TreeNode node, List<List<Integer>> res) {
            // 如果层数大于结果中个数，需要创建新的容器
            if (index > res.size()) {
                res.add(new ArrayList<>());
            }
            // 将当前节点放入当前层的集合中
            // 将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
            // res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
            res.get(index - 1).add(node.val);

            if (node.left!=null) {
                dfs(index+1, node.left, res);
            }
            if (node.right !=null) {
                dfs(index+1, node.right, res);
            }
        }
    }
}
