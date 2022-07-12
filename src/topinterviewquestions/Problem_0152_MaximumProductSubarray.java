package topinterviewquestions;

/**
 * 考虑[i]位置为结尾的情况
 * 1. 以[i]为结尾
 * 2. 不止以[i]为结尾
 *    a. [i] * i-1 位置求得的最大值
 *    b. [i] * i-1 位置求得的最小值
 *
 * 所以需要考虑每个位置的 最大累乘积 和 最小累乘积
 */
public class Problem_0152_MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        int ans = nums[0];
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            int curMax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = curMin;
            max = curMax;
            ans = Math.max(ans, max);
        }
        return ans;
    }

}
