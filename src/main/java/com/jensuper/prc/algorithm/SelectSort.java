package com.jensuper.prc.algorithm;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/08/15
 */
public class SelectSort {
    @Test
    public void test() {
        int[] arr = {10, 9, 8, 7, 6, 5};
        sort(arr);
        System.out.println(arr.toString());
    }

    public static void sort(int[] arr) {
        // 第一次遍历，处理排序数组
        for (int i = 0; i < arr.length; i++) {
            int minValue = arr[i];
            int minIndexTmp = i;
            // 第二次遍历数组，找到数组中最小的值对应的索引
            for (int j = i + 1; j < arr.length; j++) {
                int b = arr[j];
                if (b < minValue) {
                    minIndexTmp = j;
                    minValue = b;
                }
            }
            // 放在排好序位置后面
            int tmp = arr[i];
            arr[i] = arr[minIndexTmp];
            arr[minIndexTmp] = tmp;
        }
    }
}
