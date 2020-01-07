package com.jensuper.prc.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author jichao
 * @version V1.0
 * @description: 快速排序
 * @date 2019/08/21
 */
public class QuickSort {

    @Test
    public void test() {
        int[] arr = {2, 10, 7, 100, 7, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * quickSort
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        // middle num
        int middleIndex = low + (high - low) / 2;
        int provit = arr[middleIndex];

        int i = low;
        int j = high;

        while (i <= j) {

            while (arr[i] < provit) {
                i++;
            }
            while (arr[j] > provit) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        // subArry
        if (i < high) {
            quickSort(arr, i, high);
        }
        if (j > low) {
            quickSort(arr, low, j);
        }
    }

    /**
     * swap right to left
     *
     * @param arr
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
