package com.jensuper.prc.leecode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/08/16
 */
public class QueuenToStack {

    private Queue<Integer> queue1 = null;
    private Queue<Integer> queue2 = null;

    /**
     * Initialize your data structure here.
     */
    public QueuenToStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        // 将元素添加到q2中
        queue2.offer(x);
        // 如果q1不为空，将Q1添加到Q2末尾，目的是交换顺序
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // 交换q1和q2，q1存放最终数据
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue1.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue1.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
