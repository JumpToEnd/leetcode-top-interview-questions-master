package topinterviewquestions;

/**
 * 接雨水问题！求总得接水量
 * <p>
 * i位置上方能留下几格水
 * <p>
 * 水[i] = max{min{max左（不包括i位置），max右（不包括i位置）} - [i], 0}
 * <p>
 * 使用l和r指针，
 * 【预处理数组】
 * 【双指针】
 */
public class Problem_0042_TrappingRainWater {

    public static int trap(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int N = arr.length;
        int L = 1;
        // 辅助数组 左边的最大值
        int leftMax = arr[0];
        int R = N - 2;
        // 辅助数组，右边的最大值
        int rightMax = arr[N - 1];
        int water = 0;
        // l位置和r位置
        // 哪边的最大值小结算哪边的
        while (L <= R) {
            // 如果左边的最大值小于等于右边的，先结算左边的
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - arr[L]);
                leftMax = Math.max(leftMax, arr[L++]);
            }
            // 否则结算右边的
            else {
                water += Math.max(0, rightMax - arr[R]);
                rightMax = Math.max(rightMax, arr[R--]);
            }
        }
        return water;
    }

}
