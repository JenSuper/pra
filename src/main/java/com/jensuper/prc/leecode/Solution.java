package com.jensuper.prc.leecode;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;

/**
 *  141. 环形链表，给定一个链表，判断链表中是否有环。
 */
public class Solution {
    @Test
    public void test(){


    }

    /**
     * 两数之和
     * 1. 左右指针方式
     * 2. 左指针小于右指针,循环遍历
     * 3. 计算值小于目标值，left ++ ，大于目标值 right ++
     */
    public List<List<Integer>> twoSum(int[] nums, int target, int index) {
        int left = index+1;
        int right = nums.length - 1;
        List<List<Integer>> listRet = new ArrayList<>();
        while (left < right) {
            int leftValue = nums[index];
            int rightValue = nums[right];
            int sum = leftValue + rightValue;
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            }else {
                // 找到与目标值相同的
                List<Integer> list = new ArrayList<>();
                list.add(index);
                list.add(left);
                list.add(right);
                listRet.add(list);
                left++;
                right--;
            }
        }
        return listRet;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 大于0，说明没有负数，相加无法等于0
            if (num > 0) {
                break;
            }
            int target = 0 - num;
            List<List<Integer>> lists = twoSum(nums, target, i);
            ret.addAll(lists);
        }
        return ret;
    }

}
