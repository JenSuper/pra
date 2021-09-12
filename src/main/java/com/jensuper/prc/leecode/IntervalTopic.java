package com.jensuper.prc.leecode;

import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jichao
 * @version V1.0
 * @description: 区间类型
 * @date 2020/11/04
 */
public class IntervalTopic {

    public static void main(String[] args) {
        IntervalTopic intervalTopic = new IntervalTopic();
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        intervalTopic.insert(intervals, newInterval);
        System.out.println(intervals);
    }

    /**
     * 示例1
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * <p>
     * 示例2
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> arrList = new ArrayList<>();

        int len = intervals.length;
        int i = 0;

        // 从左边开始处理
        // 处理左侧不重叠区间
        while (i<len && intervals[i][1] < newInterval[0]) {
            arrList.add(intervals[i]);
            i++;
        }

        // 上面处理后，中间部分为重叠区间
        while (i < len && intervals[i][0] <= newInterval[1]) {
            // 左边最小的
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            // 右边最大的
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        arrList.add(newInterval);

        // 上面处理后，剩余为右侧不重复区间
        while (i < len && intervals[i][0] > newInterval[1]) {
            arrList.add(intervals[i]);
            i++;
        }
        return arrList.toArray(new int[0][]);
    }
}
