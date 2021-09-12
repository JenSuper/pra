package com.jensuper.prc.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jichao
 * @version V1.0
 * @description: 数组
 * @date 2020/11/02
 */
public class ArrayTopic {

    public static void main(String[] args) {
        ArrayTopic arrayTopic = new ArrayTopic();
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 2, 3};
        int[] intersection = arrayTopic.intersection(nums1, nums2);
        for (int i : intersection) {
            System.out.println(i);
        }
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // 将数组添加到集合中
        List<Integer> numListOne = new ArrayList<>();
        for (int i : nums1) {
            numListOne.add(i);
        }
        List<Integer> numListTwo = new ArrayList<>();
        for (int i : nums2) {
            numListTwo.add(i);
        }
        return getElementArr(numListOne, numListTwo);
    }

    private int[] getElementArr(List<Integer> numListOne, List<Integer> numListTwo) {
        // 遍历较长的集合
        if (numListOne.size() < numListTwo.size()) {
            getElementArr(numListTwo, numListOne);
        }
        // 取交集
        numListOne.retainAll(numListTwo);
        // 去重
        List<Integer> distinctList = numListOne.stream().distinct().collect(Collectors.toList());
        // 放入新数组
        int[] retArr = new int[distinctList.size()];
        for (int i = 0; i < distinctList.size(); i++) {
            retArr[i] = distinctList.get(i);
        }
        return retArr;
    }
}
