package topinterviewquestions;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 最长递增子序列的增强版
 * <p>
 * 每个dp里是一个map
 */
public class Problem_0673_NumberOfLongestIncreasingSubsequence {

    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp里每个都是一张表
        ArrayList<TreeMap<Integer, Integer>> dp = new ArrayList<>();

        // 进行二分寻找大于 [i] 的最左位置
        for (int i = 0; i < nums.length; i++) {
            int L = 0;
            int R = dp.size() - 1;
            int find = -1;
            while (L <= R) {
                int mid = (L + R) / 2;
                if (dp.get(mid).firstKey() >= nums[i]) {
                    find = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            int num = 1;
            // 找不到索引位置 -1，此时需要新增加一张表
            int index = find == -1 ? dp.size() : find;
            // 1.找到了索引位置
            if (index > 0) {
                TreeMap<Integer, Integer> lastMap = dp.get(index - 1);
                num = lastMap.get(lastMap.firstKey());
                if (lastMap.ceilingKey(nums[i]) != null) {
                    num -= lastMap.get(lastMap.ceilingKey(nums[i]));
                }
            }
            // 2. 需要
            if (index == dp.size()) {
                TreeMap<Integer, Integer> newMap = new TreeMap<Integer, Integer>();
                newMap.put(nums[i], num);
                dp.add(newMap);
            }
            //
            else {
                TreeMap<Integer, Integer> curMap = dp.get(index);
                curMap.put(nums[i], curMap.get(curMap.firstKey()) + num);
            }
        }
        return dp.get(dp.size() - 1).firstEntry().getValue();
    }

    public static int findNumberOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        ArrayList<TreeMap<Integer, Integer>> dp = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int L = 0;
            int R = dp.size() - 1;
            int find = -1;
            while (L <= R) {
                int mid = (L + R) / 2;
                if (dp.get(mid).firstKey() >= nums[i]) {
                    find = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }

            // 1. 此时需要开辟表
            if (find == -1) {
                // 加一个新的表
                dp.add(new TreeMap<>());
                // 取出这张表
                int index = dp.size() - 1;
                TreeMap<Integer, Integer> cur = dp.get(index);

                // 如果之前没有表，默认是1
                int size = 1;

                // 说明之前存在表，需要进行计算
                if (index > 0) {
                    // 取出前一张表
                    TreeMap<Integer, Integer> pre = dp.get(index - 1);
                    // 取出前一张表的总数量
                    size = pre.get(pre.firstKey());
                    // 取出前一张表中大于当前值的数量，扣除掉
                    if (pre.ceilingKey(nums[i]) != null) {
                        size -= pre.get(pre.ceilingKey(nums[i]));
                    }
                }
                // 将数量放入表中
                cur.put(nums[i], size);
            } else {
                int newAdd = 1;
                // 之前有表，取出上一张表中符合的记录，和上面的情况一样
                if (find > 0) {
                    TreeMap<Integer, Integer> pre = dp.get(find - 1);
                    newAdd = pre.get(pre.firstKey());
                    if (pre.ceilingKey(nums[i]) != null) {
                        newAdd -= pre.get(pre.ceilingKey(nums[i]));
                    }
                }

                // >=nums[i] ?
                // 把当前表取出来
                TreeMap<Integer, Integer> cur = dp.get(find);
                // 如果当前表的第一个key就是当前值，直接加上就可以了
                if (cur.firstKey() == nums[i]) {
                    cur.put(nums[i], cur.get(nums[i]) + newAdd);
                }
                // 否则，新插入一条记录
                else {
                    int preNum = cur.get(cur.firstKey());
                    cur.put(nums[i], newAdd + preNum);
                }
            }

        }

        // 返回最后一张表里的第一条记录就是答案
        return dp.get(dp.size() - 1).firstEntry().getValue();
    }

}
