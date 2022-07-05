package topinterviewquestions;

public class Problem_0044_WildcardMatching {

    public static boolean isMatch1(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        return process1(s, p, 0, 0);
    }

    // s[si....] 能否被 p[pi....] 匹配出来
    public static boolean process1(char[] s, char[] p, int si, int pi) {
        // 如果当前 s 是 ""
        if (si == s.length) { // s -> ""
            // 如果此时 p 也是 ""
            if (pi == p.length) { // p -> ""
                return true;
            }
            // 此时 p 还剩东西
            // pi位置一定需要是 * ，如果不是*， 一定匹配不出来！
            else {
                // p -> "..."
                // p[pi] == '*' && p[pi+1...] -> "
                return p[pi] == '*' && process1(s, p, si, pi + 1);
            }
        }

        // 如果当前 p 是 ""
        if (pi == p.length) { // p -> "" s
            return si == s.length;
        }

        // 以上是base case


        // s从si出发.... p从pi出发...
        // s[si] 肯定是 小写字母
        // p[pi] 有三种情况：小写、?、*

        // 1. 此时pi位置是小写字母
        if (p[pi] != '?' && p[pi] != '*') {
            return s[si] == p[pi] && process1(s, p, si + 1, pi + 1);
        }

        // 2. pi位置是 ？
        // si.. pi.. pi ? *
        if (p[pi] == '?') {
            return process1(s, p, si + 1, pi + 1);
        }

        // 3. pi位置是 *
        //    * 变成""，si出发0长度的前缀
        //    * 变成?，si出发1长度的前缀
        //    ....
        //    一直尝试到所有情况全部穷举
        for (int len = 0; len <= s.length - si; len++) {
            if (process1(s, p, si + len, pi + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMatch2(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        // base case
        dp[N][M] = true;

        // si == s.length时，最后一行每个位置都依赖右侧的位置
        for (int pi = M - 1; pi >= 0; pi--) {
            dp[N][pi] = p[pi] == '*' && dp[N][pi + 1];
        }

        // pi == s.length 时，只有 dp[N][M] = true，之前已经设置过了，其他位置默认就是true

        // 普遍位置
        // 依赖，右侧从当前行开始向下的一整列
        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                if (p[pi] != '?' && p[pi] != '*') {
                    dp[si][pi] = s[si] == p[pi] && dp[si + 1][pi + 1];
                    continue;
                }
                if (p[pi] == '?') {
                    dp[si][pi] = dp[si + 1][pi + 1];
                    continue;
                }
                // p[pi] == '*'
                // 斜率优化
                dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
            }
        }
        return dp[0][0];
    }

    // 最终做的化简
    public static boolean isMatch3(String str, String pattern) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int pi = M - 1; pi >= 0; pi--) {
            dp[N][pi] = p[pi] == '*' && dp[N][pi + 1];
        }
        for (int si = N - 1; si >= 0; si--) {
            for (int pi = M - 1; pi >= 0; pi--) {
                if (p[pi] != '*') {
                    dp[si][pi] = (p[pi] == '?' || s[si] == p[pi]) && dp[si + 1][pi + 1];
                } else {
                    dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
                }
            }
        }
        return dp[0][0];
    }

}
