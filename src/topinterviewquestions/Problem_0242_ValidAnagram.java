package topinterviewquestions;

/**
 *
 */
public class Problem_0242_ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] tar = t.toCharArray();
        int[] count = new int[256];
        for (char cha : str) {
            count[cha]++;
        }
        for (char cha : tar) {
            // 优化点，如果出现负数 直接结束
            if (--count[cha] < 0) {
                return false;
            }
        }

        // 只要没出现负数并且统计完成，直接 返回 true
        return true;
    }

}
