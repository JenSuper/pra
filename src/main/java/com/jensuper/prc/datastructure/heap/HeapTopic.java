package com.jensuper.prc.datastructure.heap;

import static org.apache.commons.lang3.ArrayUtils.swap;

/**
 * @author jichao
 * @version V1.0
 * @description:堆
 * @date 2021/03/16
 */
public class HeapTopic {

    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public HeapTopic(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        // 堆满了
        if (count >= n) {
            return;
        }
        ++count;
        // 放入最后面
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
            swap(a, i, i / 2); // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
            i = i / 2;
        }
    }

    public static void main(String[] args) {
        HeapTopic heapTopic = new HeapTopic(10);
        heapTopic.insert(1);
        heapTopic.insert(2);
        heapTopic.insert(3);
    }
}
