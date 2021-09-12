package com.jensuper.prc.leecode;

import java.util.Stack;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/05/19
 */
public class StackToQueue2 {

    private  Stack<Object> inputStack = new Stack<>();
    private  Stack<Object> outputStack = new Stack<>();

    // 添加到队列最后一个
    public void push(Object item) {
        inputStack.push(item);
    }

    // 删除队列第一个
    public Object pop() {
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

    // 查看队列第一个
    public Object peek() {
        if (outputStack.empty() && inputStack.empty()) {
            return null;
        }
        // 如果出栈无元素，则需要将入栈元素导入
        if (outputStack.empty()) {
            while (inputStack.size()>0) {
                outputStack.push(inputStack.pop());
            }
        }
        return outputStack.peek();
    }

    // 判断队列是否为空
    public boolean empty() {
        if (inputStack.size()== 0 && outputStack.size() ==0) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        StackToQueue2 stackToQueue2 = new StackToQueue2();
        System.out.println(stackToQueue2.empty());
        stackToQueue2.push(1);
        stackToQueue2.push(2);

        stackToQueue2.inputStack.forEach(s-> System.out.println(s));
//        System.out.println(stackToQueue2.peek());
//        System.out.println(stackToQueue2.pop());
//        System.out.println(stackToQueue2.peek());
    }
}
