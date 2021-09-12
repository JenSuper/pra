package com.jensuper.prc.leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/12/04
 */
public class PossibleTopic {

    /**
     * 659. 分割数组为连续子序列
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
     *
     * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
     *
     * 示例 1：
     *
     * 输入: [1,2,3,3,4,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3
     * 3, 4, 5
     *
     *
     * 示例 2：
     *
     * 输入: [1,2,3,3,4,4,5,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     *
     *
     * 示例 3：
     *
     * 输入: [1,2,3,4,4,5]
     * 输出: False
     *
     *
     * 提示：
     *
     * 输入的数组长度范围为 [1, 10000]
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        // 时间复杂度：数据规模与程序执行时间
        int length = nums.length;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            list1.add(num);
            // 包含说明是重复的


        }
        return false;
    }
}
