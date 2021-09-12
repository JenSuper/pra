package com.jensuper.prc.leecode;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 328. 奇偶链表
 * @date 2020/11/13
 */
public class linkSolutionTopic {
    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * 示例 1:
     *
     * 输入: 1->2->3->4->5->NULL    1 3 5
     * 输出: 1->3->5->2->4->NULL
     *
     * 示例 2:
     *
     * 输入: 2->1->3->5->6->4->7->NULL   2 3 6 7
     * 输出: 2->3->6->7->1->5->4->NULL
     *
     * 说明:
     *
     * 应当保持奇数节点和偶数节点的相对顺序。
     * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     *
     */

    @Test
    public void test(){
        ListNode listNode5 = new ListNode(5,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        oddEvenList(listNode1);
        System.out.println();
    }
    
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        // 将节点进行奇数和偶数的分离

        // 偶数头结点
        ListNode eventHead = head.next;

        // 奇数指针
        ListNode odd = head;
        // 偶数指针
        ListNode event = eventHead;


        while (event !=null && event.next !=null) {
            // 奇数的下一个节点为偶数的下一个节点,并且移动指针
            odd.next = event.next;
            odd = odd.next;
            // 偶数的下一个节点为奇数的下一个节点，并且移动指针
            event.next = odd.next;
            event = event.next;
        }


        odd.next = eventHead;


        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
