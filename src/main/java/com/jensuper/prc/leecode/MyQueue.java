package com.jensuper.prc.leecode;

import java.util.Stack;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/05/19
 */
public class MyQueue {
    /** Initialize your data structure here. */
    private  Stack<Integer> inputStack = new Stack<>();
    private  Stack<Integer> outputStack = new Stack<>();
    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
// 出栈与入栈无元素，抛异常
        if (outputStack.empty() && inputStack.empty()) {
            throw new RuntimeException("queue is empty");
        }
        // 如果出栈无元素，则需要将入栈元素导入
        if (outputStack.empty()) {
            while (inputStack.size()>0) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outputStack.empty() && inputStack.empty()) {
            return 0;
        }
        // 如果出栈无元素，则需要将入栈元素导入
        if (outputStack.empty()) {
            while (inputStack.size()>0) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (inputStack.size()== 0 && outputStack.size() ==0) {
            return true;
        }
        return false;
    }
}
