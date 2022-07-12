package topinterviewquestions;

import java.util.HashMap;

public class Problem_0128_LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        // map中只存储开头和结尾的长度信息
        HashMap<Integer, Integer> map = new HashMap<>();

        // 最长长度
        int len = 0;

        // 开始遍历数组
        for (int num : nums) {
            // 如果map中没有出现过num
            if (!map.containsKey(num)) {
                // 将当前数字放入map， 长度为 1
                map.put(num, 1);
                // 从map中获取之前的长度
                int preLen = map.getOrDefault(num - 1, 0);
                // 从map中获取之后的长度
                int posLen = map.getOrDefault(num + 1, 0);
                // 计算总长度
                int all = preLen + posLen + 1;
                // 将开头位置放进map
                map.put(num - preLen, all);
                // 将结尾位置放进map
                map.put(num + posLen, all);
                len = Math.max(len, all);
            }
        }
        return len;
    }

}
