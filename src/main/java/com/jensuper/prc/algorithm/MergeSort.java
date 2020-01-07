package com.jensuper.prc.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author jichao
 * @version V1.0
 * @description: 归并排序
 * @date 2019/08/26
 */
public class MergeSort {

    @Test
    public void test() {
        int[] arr = {10, 9, 8, 7, 6, 5};
        this.mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int leftIndex, int rigthIndex) {
        if (leftIndex >= rigthIndex) {
            return;
        }
        int middleIndex = (rigthIndex + leftIndex) / 2;
        // 归并左边
        mergeSort(arr, leftIndex, middleIndex);
        // 归并右边
        mergeSort(arr, middleIndex + 1, rigthIndex);
        // 合并
        merge(arr, leftIndex, middleIndex, rigthIndex);
//        meger(arr, leftIndex, middleIndex, rigthIndex);
    }

    /**
     * 合并
     *
     * @param arr
     * @param leftIndex
     * @param middleIndex
     * @param rigthIndex
     */
    private void merge(int[] arr, int leftIndex, int middleIndex, int rigthIndex) {
        if (leftIndex > middleIndex || middleIndex > rigthIndex) {
            return;
        }
        int tmpLength = rigthIndex - leftIndex + 1;
        int[] tempArr = new int[tmpLength];

        // 将左侧和右侧的数组放入临时数组中
        int i = leftIndex;
        int j = middleIndex + 1;
        int k = 0;
        while (i <= middleIndex && j <= rigthIndex) {
            tempArr[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        // 如果左侧还有剩余
        while (i <= middleIndex) {
            tempArr[k++] = arr[i++];
        }
        // 如果右侧还有剩余
        while (j <= rigthIndex) {
            tempArr[k++] = arr[j++];
        }

        // 将临时数组赋值给原数组
        for (int n = 0; n < tmpLength; n++) {
            arr[leftIndex + n] = tempArr[n];
        }
        System.out.println(Arrays.toString(arr));
    }
}
