package com.jensuper.prc.leecode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 922. 按奇偶排序数组 II
 * @date 2020/11/12
 */
public class ArraySortTopic {

    /**
     * 
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
     *
     * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
     *
     * 你可以返回任何满足上述条件的数组作为答案。
     *
     * 示例：
     *
     * 输入：[4,2,5,7]
     * 输出：[4,5,2,7]
     * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     *
     * 提示：
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     */

    @Test
    public void test(){
        int[] arr = {4, 2, 5, 7};
        sortArrayByParityII(arr);
    }

    public int[] sortArrayByParityII(int[] arr){
        int length = arr.length;
        int[] arrRet = new int[length];

        // 偶数
        int startIndexOne = 0;
        for (int i : arr) {
            if (i%2==0) {
                arrRet[startIndexOne] = i;
                startIndexOne += 2;
            }
        }

        // 奇数
        int startIndexTwo = 1;
        for (int i : arr) {
            if (i%2==1) {
                arrRet[startIndexTwo] = i;
                startIndexTwo += 2;
            }
        }
        return arrRet;
    }

    public int[] sortArrayByParityIII(int[] arr){


        for (int i = 0; i < arr.length; i++) {
            // 偶数
            if (i % 2 == 0) {
                int ele = arr[i];
                if (ele %2 ==0) {
                    continue;
                }
                int tmpIndex = i;
                while (tmpIndex < arr.length) {
                    tmpIndex++;
                    int eleTmp = arr[tmpIndex];
                    if (eleTmp % 2 ==0) {
                        int tmp = arr[i];
                        arr[i] = arr[tmpIndex];
                        arr[tmpIndex] = tmp;
                        break;
                    }
                }
            }else{
                // 奇数
                int ele = arr[i];
                if (ele %2 != 0) {
                    continue;
                }
                int tmpIndex = i;
                while (tmpIndex < arr.length) {
                    tmpIndex++;
                    int eleTmp = arr[tmpIndex];
                    if (eleTmp % 2 !=0) {
                        int tmp = arr[i];
                        arr[i] = arr[tmpIndex];
                        arr[tmpIndex] = tmp;
                        break;
                    }
                }
            }
        }
        return arr;
    }
    
    
    public int[] sort(int[] arr){
        // 校验
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return arr;
        }

        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }

        /**
         * 奇数、偶数 分类
         */
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();

        list.forEach(data->{
            if (data % 2 ==0) {
                listTwo.add(data);
            }else{
                listOne.add(data);
            }
        });


        /**
         * 重构原数组
         */
        boolean flag = true;
        int indexOne = 0;
        int indexTwo = 0;
        for (int i = 0; i < arr.length; i++) {
            // 偶数
            if (flag) {
                arr[i] = listTwo.get(indexTwo);
                flag = false;
                indexTwo++;
            }else {
                // 奇数
                arr[i] = listOne.get(indexOne);
                flag = true;
                indexOne++;
            }
        }
        return arr;
    }
    
    
}
