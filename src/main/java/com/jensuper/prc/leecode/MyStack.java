package com.jensuper.prc.leecode;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/05/19
 */
public class MyStack {


    private LinkedList<Integer> inputQueue = new LinkedList<>();
    private LinkedList<Integer> tmpQueue = new LinkedList<>();

    /**
     * Initialize your data structure here.
     */
    public MyStack() {

    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        // 临时队列为空，直接添加
        if (tmpQueue.isEmpty()) {
            tmpQueue.add(x);
        }else{
            inputQueue.add(x);
            // 把临时队列放入input
            while (!tmpQueue.isEmpty()) {
                inputQueue.add(tmpQueue.poll());
            }
            // 将新添加的元素放入input
            tmpQueue = inputQueue;
            inputQueue = new LinkedList<>();
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        if (tmpQueue.isEmpty()) {
            return -1;
        }
        return tmpQueue.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        if (tmpQueue.isEmpty()) {
            return -1;
        }
        return tmpQueue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        if (tmpQueue.isEmpty()) {
            return true;
        }
        return false;
    }
    
    
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top());
    }
}
