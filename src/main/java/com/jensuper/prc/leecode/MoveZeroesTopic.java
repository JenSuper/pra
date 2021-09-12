package com.jensuper.prc.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jichao
 * @version V1.0
 * @description:
 * @date 2020/11/19
 */
public class MoveZeroesTopic {
    /**
     * 283. 移动零
     *
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 示例:
     *
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     *
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     *
     */

    public static void main(String[] args) {
        String s = "hdfs://server03:8020/user/hive/warehouse/";
        String regex = "hdfs://.*?/";
        Matcher matcher = Pattern.compile(regex).matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
//        int[] nums = {0, 1, 0, 3, 12};
//        MoveZeroesTopic moveZeroesTopic = new MoveZeroesTopic();
//        moveZeroesTopic.moveZeroes(nums);
    }
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 获取 0 位置的索引
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                i++;
                for (int j = i; j < nums.length; j++) {
                    nums[i] = nums[j];
                }
            }
        }
        System.out.println();
    }

}
