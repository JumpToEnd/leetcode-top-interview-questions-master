package topinterviewquestions;

/**
 * 不用除法
 * <p>
 * 每个位置求  除了自己之外的累乘积
 */
public class Problem_0238_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        // 出现0的次数
        int zeros = 0;
        // 除了0之外的累乘积
        int all = 1;
        for (int num : nums) {
            if (num == 0) {
                zeros++;
            } else {
                all *= num;
            }
        }
        // 如果原数组中0的个数大于1
        if (zeros > 1) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
        // 原数组中的0的个数小于等于1
        else {
            // 原数组中没有0
            if (zeros == 0) {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = all / nums[i];
                }
            }
            // 原数组中只有1个0
            else {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = nums[i] == 0 ? all : 0;
                }
            }
        }
        return nums;
    }

    /**
     * 不用除法的方式
     * <p>
     * 从左至右求一个累乘积 left[]
     * 从右至左求一个累乘积 right[]
     * <p>
     * [i]的答案就是  left[i-1] * right[i+1]
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i];
        }
        int right = 1;
        for (int i = n - 1; i > 0; i--) {
            ans[i] = ans[i - 1] * right;
            right *= nums[i];
        }
        ans[0] = right;
        return ans;
    }

}
