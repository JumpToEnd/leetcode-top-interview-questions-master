package topinterviewquestions;

/**
 * 计算质数
 * <p>
 * 只需要验证 2...根号x 就可以了
 * <p>
 * 筛查法
 * <p>
 * 所有的偶数都不是质数
 */
public class Problem_0204_CountPrimes {

    public static int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        // 记录筛查结果，true代表不是质数，false代表是质数
        boolean[] f = new boolean[n];
        // 把偶数先画叉，剩余质数最多 n/2 个
        int count = n / 2;

        // 只需要验证奇数
        for (int i = 3; i * i < n; i += 2) {
            // 如果这个位置不是质数，跳过
            if (f[i]) {
                continue;
            }

            // j的位置就开始画叉
            // j 从 i*i 开始，每次增加到 j + 2 * i
            // 偶数位置已经在之前全部画完叉了，只需要关心奇数位置
            for (int j = i * i; j < n; j += 2 * i) {
                // 如果当前代表的质数，那么把它改为非质数，然后count--
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }

}
