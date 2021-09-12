package com.jensuper.prc.datastructure;

/**
 * 链表反转 剑指offer 24
 */
public class LinkedRevert2 {

    public ListNode reverseList(ListNode head) {
        // 前驱节点
        ListNode prev = null;
        // 当前节点,互换位置
        ListNode curr = head;
        while (curr!=null) {
            // 下一个节点，临时记录
            ListNode next = curr.next;
            // 把下一个节点，指向上一个节点
            curr.next = prev;

            // 上一个节点指向,当前
            prev = curr;
            curr = next;
        }
        return prev;

    }

      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}
