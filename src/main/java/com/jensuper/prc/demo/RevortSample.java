package com.jensuper.prc.demo;


import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/04/29
 */
public class RevortSample {

    private static Stack cancelQueue = new Stack();
    private static Stack recoverQueue = new Stack();

    public static void main(String[] args) {
        ArrayDeque<String> cancelQueue = new ArrayDeque<>();
        cancelQueue.push("1");
        cancelQueue.push("2");
        cancelQueue.push("3");
        cancelQueue.push("4");
        System.out.println(cancelQueue);
        System.out.println(cancelQueue.peek());
        System.out.println(cancelQueue);

//        System.out.println(cancelQueue.getFirst());
//        System.out.println(cancelQueue.getLast());
    }

    public static void main11(String[] args) {
        save("1");
        save("2");
        save("3");
        save("4");
        System.out.println(get("cancel"));
        System.out.println(get("cancel"));
        System.out.println(get("111"));
        System.out.println(11111111);
    }


    public static void save(String data) {
        cancelQueue.push(data);
        recoverQueue = new Stack();
    }

    public static Object get(String type) {
        if (type.equals("cancel")) {
            Object pop = cancelQueue.pop();
            recoverQueue.push(pop);
            return cancelQueue.get(cancelQueue.size() - 1);
        }else {
            Object pop = recoverQueue.pop();
            cancelQueue.push(pop);
            return cancelQueue.get(cancelQueue.size() - 1);
        }

    }
    @Test
    public void test22(){
        List<String> cpus = new ArrayList<>();
        cpus.add("e622a6dc3c545bdfda29d743c8434530");
                List<String> split = new ArrayList<>();
        split.add("e622a6dc3c545bdfda29d743c8434530");
        
    }
}
