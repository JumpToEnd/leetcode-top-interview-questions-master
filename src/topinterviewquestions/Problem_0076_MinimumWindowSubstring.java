package topinterviewquestions;

/**
 * 欠账表
 * <p>
 * 窗口技巧：左闭右开
 */
public class Problem_0076_MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        char[] str = s.toCharArray();
        char[] target = t.toCharArray();
        int[] map = new int[256];
        for (char cha : target) {
            map[cha]++;
        }
        int all = target.length;
        int L = 0;
        int R = 0;
        // -1(从来没找到过合法的)
        int minLen = -1;
        // 抓一下最小长度的字符串的起始和结束位置
        int ansl = -1;
        int ansr = -1;
        // [L..R) 左闭右开  [0,0)  R => 即将要把这个字符拉进窗口的意思
        while (R != str.length) {
            map[str[R]]--;
            // 没还完
            if (map[str[R]] >= 0) {
                all--;
            }
            // 还完了进行结算
            if (all == 0) {
                // 如果词频此时<0，
                // 说明多给的，原本就不欠
                // 此时L++，同时L位置的词频++
                // L向左滑
                while (map[str[L]] < 0) {
                    map[str[L++]]++;
                }
                // 跳出循环，[L] 词频 等于 0

                // 之前没有抓到过答案
                // 或者之前的答案没有现在的好
                // 更新答案
                if (minLen == -1 || minLen > R - L + 1) {
                    minLen = R - L + 1;
                    ansl = L;
                    ansr = R;
                }
                // str[L] 必定属于 target
                // 此时L已经向右滑了，all++，重新变成欠账的状态
                all++;
                // 对应位置的词频++，然后L来到下个位置，继续开始
                map[str[L++]]++;
            }
            R++;
        }
        return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
    }

}
