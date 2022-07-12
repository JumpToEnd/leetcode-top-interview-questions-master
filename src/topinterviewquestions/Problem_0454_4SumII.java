package topinterviewquestions;

import java.util.HashMap;

/**
 * 4个数组每个数组拿一个数，相加等于0
 * <p>
 * 分治思想
 * <p>
 * 两个数组组合
 */
public class Problem_0454_4SumII {

    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        // 前两个数组组合，把结果记录在map里
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                sum = A[i] + B[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }
        // 后两个数组组合，同时统计结果
        int ans = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                sum = C[i] + D[j];
                if (map.containsKey(-sum)) {
                    ans += map.get(-sum);
                }
            }
        }
        return ans;
    }

}
