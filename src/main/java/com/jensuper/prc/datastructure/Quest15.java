package com.jensuper.prc.datastructure;

import java.util.*;

/**
 * @author jichao
 * @version V1.0
 * @description: 三数之和
 * @date 2021/06/02
 */
public class Quest15 {
    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 1. 实现两数之和 = 目标值
     * 2. 使用双端指针： left right
     * 3. 遍历数组，拿到当前元素 0 - current = 目标值，调用两数之和
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> twoSum(int[] nums, int target,int index) {
        List<List<Integer>> ret = new ArrayList<>();
        int left = index + 1;
        int right = nums.length - 1;

        // 如果左指针索引小于右指针
        while (left < right) {
            int leftValue = nums[left];
            int rightValue = nums[right];
            int sum =  leftValue + rightValue;
            // 判断与目标值大小：小，左指针++，大,右指针--,相等，同时移动
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }else {
                List<Integer> currentList = new ArrayList<>();
                currentList.add(nums[index]);
                currentList.add(leftValue);
                currentList.add(rightValue);
                ret.add(currentList);
                left++;
                right--;
            }
        }
        return ret;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length ==0 || nums.length ==1) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                break;
            }
            // 去重使用
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = 0 - num;
            List<List<Integer>> threeRet = twoSum(nums, target, i);
            ret.addAll(threeRet);
        }
        return ret;
    }
    public static void main(String[] args) {
        Quest15 quest1 = new Quest15();
        int[] nums = {1, 0, -2, 2, 9,1};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
}
