package com.jensuper.prc.datastructure;

import java.util.Stack;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/12/10
 */
public class StackTopic {

    /**
     * 3+5*8-6
     * @param args
     */
    public static void main(String[] args) {
        Stack<Integer> numSk = new Stack<>();
        Stack<String> symbolSk = new Stack<>();

        numSk.push(3);
        symbolSk.push("+");
        numSk.push(5);
        symbolSk.push("*");

        numSk.push(8);

        String pop2 = symbolSk.pop();
        Integer pop = numSk.pop();
        Integer pop1 = numSk.pop();
    }
}
