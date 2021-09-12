package com.jensuper.prc.datastructure.heap;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/03/23
 */
public class KthLargestTopic {

    PriorityQueue<Integer> pq;
    int k;

    public KthLargestTopic(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
