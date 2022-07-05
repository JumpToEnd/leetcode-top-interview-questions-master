package topinterviewquestions;

/**
 * 设置 l和r指针
 * l ， 0 到 l-1 位置，都已经做到i位置放的是i+1
 * r ， r+1 => 假设最好预期下，尽可能大的最小确实的正整数，假设没有看过的数字全部都是合格的
 *
 * 【荷兰国旗问题的优化！】
 *
 *
 * 面试场上如果遇到自己做过的题
 * 1. 不要说自己做过！！
 * 2. 思考如果使用一个朴素的思想来优化自己的解法
 */
public class Problem_0041_FirstMissingPositive {
    public static int firstMissingPositive(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            }
            // 不需要的情况：
            // 1. 当前位置大于 r => arr[l] > r
            // 2. 当前位置小于等于 l => arr[l] <= l
            // 3. 如果l位置的数该在的位置已经有了该有的数 => arr[arr[l] - 1] == arr[l]
            // 满足其中一种 => 把 l 位置的数字 和 r 位置进行交换，然后把 r-- ， 代表 预期变差
            else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                swap(arr, l, --r);
            }
            // 把l位置上的数 交换到 该去的位置
            else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
