package topinterviewquestions;

/**
 *
 */
public class Problem_0188_BestTimeToBuyAndSellStockIV {

    /**
     * 动态规划
     * 当 k >= n/2 之后，就相当于无限次交易
     *
     * @param K
     * @param arr
     * @return
     */
    public static int maxProfit1(int K, int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        if (K >= N / 2) {
            return allTrans(arr);
        }
        // dp[i][j] =>  从 0...i的范围上，做不超过j次交易获得的最大收益
        // 情况分析：
        // 1. 如果[i]位置不参与交易  ==> dp[i-1][j]
        // 2. [i]位置参与交易，参与最后一次交易的卖出时机
        //    a. 最后一次交易的买入时机就在[i]位置  ==>   [0...i] 做了k-1次交易，然后[i]位置买入，[i]位置卖出
        //    b. 最后买入的时机是[i-1]位置  ==>  [0...i-1] 做了k-1次交易，然后[i-1]位置买入，[i]位置卖出
        //    c. 最后买入的时机是[i-2]位置  ==>  [0...i-2] 做了k-1次交易，然后[i-2]位置买入，[i]位置卖出
        //    ....
        //    z. 最后买入的时机是[0]位置 ==> [0]做了k-1次交易，然后[0]位置买入，[i]位置卖出
        int[][] dp = new int[N][K + 1];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= K; j++) {
                //  情况1
                dp[i][j] = dp[i - 1][j];
                // 情况2
                for (int p = 0; p <= i; p++) {
                    dp[i][j] = Math.max(dp[p][j - 1] + arr[i] - arr[p], dp[i][j]);
                }
            }
        }
        return dp[N - 1][K];
    }

    /**
     * 进行了斜率优化
     */
    public static int maxProfit2(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int j = 1; j <= K; j++) {
            int pre = dp[j][0];
            int best = pre - prices[0];
            for (int i = 1; i < N; i++) {
                pre = dp[j - 1][i];
                dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
                best = Math.max(best, pre - prices[i]);
                ans = Math.max(dp[j][i], ans);
            }
        }
        return ans;
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

}
