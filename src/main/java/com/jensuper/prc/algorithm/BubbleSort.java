package com.jensuper.prc.algorithm;

import org.junit.Test;

/**
 * @author jichao
 * @version V1.0
 * @description: 冒泡排序
 * @date 2019/08/21
 */
public class BubbleSort {

    @Test
    public void test() {

        Integer[] arr = {6,5,4,3,2,1};

        //记录遍历次数
        int count = 0;
        //记录临时变量
        int temp;
        //0 未修改，顺序已经排列好了
        int changeFlag;

        //循环次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 初始化排序状态
            changeFlag = 0;
            // 比较大小
            for (int j = 0; j < arr.length - i - 1; j++) {
                System.out.println("i="+i+" j="+j);
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    changeFlag = 1;
                }
                for (Integer integer : arr) {
                    System.out.println(integer);
                }
            }
            if (changeFlag == 0) {
                break;
            }
            count++;
        }
        System.out.printf("遍历次数，%d", count);
        System.out.println(arr);
    }
}
