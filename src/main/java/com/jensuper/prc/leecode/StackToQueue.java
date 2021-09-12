package com.jensuper.prc.leecode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/08/16
 */
public class StackToQueue {
    // 添加数据栈
    Deque<Integer> inStack = null;
    // 查询数据栈
    Deque<Integer> outStack = null;
    /** Initialize your data structure here. */
    public StackToQueue() {
        inStack = new LinkedList<>();
        outStack = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            convert();
        }
        return outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            convert();
        }
        return outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    /**
     * 将入栈数据放入出栈数据中
     */
    private void convert() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.poll());
        }
    }
}
