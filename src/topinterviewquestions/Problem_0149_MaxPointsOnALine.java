package topinterviewquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * 对于某个点[i, j]，考虑后面的点
 * <p>
 * 1. 和[i, j]为同一个点   => b个
 * 2. 和[i, j]构成某种斜率  => 斜率map中最多的点  甲
 * <p>
 * 对于[i, j]的答案就是 甲 + b
 * <p>
 * 优化点：
 * 每个点只需要看后面的点就可以了，不需要再看之前的点，因为之前的点答案已经求过了
 * <p>
 * 斜率的表示:
 * 不能使用double类型来表示斜率，因为精度问题会导致准确度有问题
 * <p>
 * 比如：7/70
 * 那么分子分母都除以最大公约数，化成最简形式  1/10
 * 然后用字符串形式表示 "1_10"
 */
public class Problem_0149_MaxPointsOnALine {

    public static int maxPoints(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        // 斜率Map，
        // 使用字符串表示，key为字符串形式的斜率"1_10"，value为点数量
        // Map<String, Integer>   "3_5"   6
        // 3 / 5    4
        // 3 / 7    10
        // 3 / 17   11
        // 5 / 7    9
        // 5 / 9    3
        // 使用嵌套Map表示，最外层的Integer为分子，内层key为分母，value为点数量
        // Map<Integer, Map<Integer, Integer>> map
        // 3 :    (  5 , 4    7, 10,      17 ,  11      )
        // 5 :    (  7 , 9    9, 3  )
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            // 共位置的点
            int samePosition = 1;
            // 相同x的值的点，斜率为∞，单独表示
            int sameX = 0;
            // 相同y的值的点，斜率为0，单独表示
            int sameY = 0;
            // 哪个斜率压中的点最多，把最多的点的数量，赋值给line
            int line = 0;

            //
            for (int j = i + 1; j < points.length; j++) {
                // 求差值
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];

                // 相同点
                if (x == 0 && y == 0) {
                    samePosition++;
                }
                // 相同x的值
                else if (x == 0) {
                    sameX++;
                }
                // 相同y的值
                else if (y == 0) {
                    sameY++;
                }
                // 普通有斜率的值
                else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<Integer, Integer>());
                    }
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePosition);
        }
        return result;
    }

    // 保证初始调用的时候，a和b不等于0
    // O(1)
    // 背住！辗转相除法
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
