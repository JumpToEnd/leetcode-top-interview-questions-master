package topinterviewquestions;

/**
 * 最长的没有重复字符的子串
 * <p>
 * 从左向右尝试
 * <p>
 * <p>
 * i位置为结尾，关注两个信息，[i-1] 和 [i]上次出现的位置
 * <p>
 * 结果 =>  Math.min([i-1], map[i]);
 */
public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.equals("")) {
            return 0;
        }


        char[] str = s.toCharArray();

        // 使用数组来记录字符上次出现的位置，初始化全部为-1
        // 比如 'a' 上次出现的位置是 17
        // 那么，map[97] = 17
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }

        // 收集答案
        int len = 0;
        // i-1位置结尾的情况下，往左推，推不动的位置是谁
        int pre = -1;
        //
        int cur = 0;
        for (int i = 0; i != str.length; i++) {
            // i位置结尾的情况下，往左推，推不动的位置是谁
            // pre (i-1信息) -> pre(i 结尾信息)
            pre = Math.max(pre, map[str[i]]);
            // 计算本次的长度
            cur = i - pre;
            len = Math.max(len, cur);
            map[str[i]] = i;
        }
        return len;
    }

}
