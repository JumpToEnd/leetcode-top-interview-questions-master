package topinterviewquestions;

public class Problem_0123_BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int ans = 0;
        // 做完一次交易 并且 买入 的价格 最大值
        int doneOnceMinusBuyMax = -prices[0];
        // 做完一次交易所获得最优收入
        int doneOnceMax = 0;// 0 : [0] - [0]
        // 0...i 位置的最小值
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {

            ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
            min = Math.min(min, prices[i]);
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            // 两种可能性：
            // 1. 最后一次交易是在i位置买入 => doneOnceMax - prices[i]
            // 2. 最后一次交易不是在i位置买入 => [i-1] doneOnceMinusBuyMax
            doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
        }
        return ans;
    }

}
