package com.jensuper.prc.datastructure;

/**
 * @author jichao
 * @version V1.0
 * @description: 122. 买卖股票的最佳时机 II (深度遍历 贪心算法 动态规划)
 *
 * @date 2021/06/26
 */
public class Quest122 {
    /**
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     */


    /**
     * 贪心算法：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
     *
     * 比较当天和前一天的价格，取最大值
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        // 贪心的角度考虑我们每次选择贡献大于 0即能使得答案最大化
        // 计算当前价格与上一天价格之差是否大于0
        for (int i = 1; i < prices.length; i++) {
            int max = Math.max(0, prices[i] - prices[i - 1]);
            sum += max;
        }
        return sum;
    }

    /**
         * 动态规划：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
     *
     *  设置一个二维矩阵表示状态。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // [i][j] i:第几天 j: 0 持有现金 1 持有股票
        int dp[][] = new int[prices.length][2];

        // 第0天，如果不持有股票，拥有现金
        dp[0][0] = 0;
        // 第0天，如果持有股票，当前拥有的现金数是当天股价的相反数
        dp[0][1] = -prices[0];
        /**
         *
         *      7,1,5,3,6,4
         *  0   0
         *  1   -7
         *
         */
        for (int i = 1; i < dp.length; i++) {
            // 不持有股票的现金: 昨日现金 与 （昨天股票+当前现金） 求最大值
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 持有股票的股票：昨日股票 与 （昨日价格 - 今日价格） 求最大值，
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }


    
    public static void main(String[] args) {
        Quest122 quest = new Quest122();
        System.out.println(quest.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(quest.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
