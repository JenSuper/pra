package com.jensuper.prc.datastructure;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/12/10
 */
public class LinkedRevert {

    @Test
    public void test(){
        Node node3 = new Node(null,3);
        Node node2 = new Node(node3,2);
        Node node1 = new Node(node2,1);

        Node revert = this.revert(node1);
        System.out.println();
    }

    private Node revert(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        // 前驱节点
        Node pre = null;
        // 当前节点
        Node cur = node;
        // 临时头结点
        Node tmpHead;

        // 1 -> 2 -> 3 -> 4
        while (cur != null) {
            // 修改当前指针,指向下一个指针
            tmpHead = cur.next; // 2
            cur.next = pre;

            pre = cur;
            cur = tmpHead;
        }
        return pre;
    }


    class Node {
        private Object value;
        private Node next;

        public Node(Node next, Object value) {
            this.next = next;
            this.value = value;
        }
    }
}
