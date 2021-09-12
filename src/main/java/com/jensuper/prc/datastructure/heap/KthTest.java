package com.jensuper.prc.datastructure.heap;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/03/23
 */
public class KthTest {
    @Test
    public void test(){
        int[] nums = {4, 5, 8, 2};
        KthLargestTopic kthLargestTopic = new KthLargestTopic(3, nums);
        System.out.println(kthLargestTopic.add(3));// return 4
        System.out.println(kthLargestTopic.add(5));// return 5
        System.out.println(kthLargestTopic.add(10));// return 5
        System.out.println(kthLargestTopic.add(9));// return 8
        System.out.println(kthLargestTopic.add(4));// return 8
    }
}
