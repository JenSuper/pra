package com.jensuper.prc.datastructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2021/06/02
 */
public class Quest1 {
    /**
     * 两数之和
     * 1. 遍历数组
     * 2. 目标值 - 数组当前值 = 预期值
     * 3. 判断预期值是否在map中，如果在，则返回当前值和预期值对应的索引，如果不在，把当前值放入到map<值，索引>
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length ==0 || nums.length ==1) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>(1);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int ret = target - num;
            if (map.containsKey(ret)) {
                return new int[]{i,map.get(ret)};
            }
            map.put(num, i);
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        Quest1 quest1 = new Quest1();
        int[] nums = {1, 2, 5, 7, 9};
        int[] ints = quest1.twoSum(nums, 10);
        System.out.println(ints);
    }
}
