package topinterviewquestions;

/**
 * 最长递增子序列
 * <p>
 * 以 i 位置为结尾来计算结果，结果必在其中
 * <p>
 * dp[i] 的值 等于 0...i-1 区间内，小于 [i] 的最长递增子序列值 然后 +1
 * <p>
 * 这样每到一个 i 位置，就需要遍历一下左边所有数据，时间复杂度为 O(N^2)
 * <p>
 * 优化方案：
 * 使用一个辅助数组 ends[] 来帮助快速查找 0...i-1 区间内，小于[i]的最长递增子序列
 * ends[i] 表示 所有长度为 i+1 的递增子序列的最小结尾
 * 
 */
public class Problem_0300_LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // ends[i] => 所有长度为 i+1 的递增子序列的最小结尾
        //
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

}
