package topinterviewquestions;

/**
 * 翻转整数
 * <p>
 * 思路：
 * 一定要避免越界
 * <p>
 * 把所有整数都转换为负数计算
 * <p>
 * 中间要避免越界
 */
public class Problem_0007_ReverseInteger {

    public static int reverse(int x) {
        boolean neg = ((x >>> 31) & 1) == 1;
        x = neg ? x : -x;
        int m = Integer.MIN_VALUE / 10;
        int o = Integer.MIN_VALUE % 10;
        int res = 0;
        while (x != 0) {
            if (res < m || (res == m && x % 10 < o)) {
                return 0;
            }
            res = res * 10 + x % 10;
            x /= 10;
        }
        return neg ? res : Math.abs(res);
    }

}
