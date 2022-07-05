package topinterviewquestions;

public class Problem_0029_DivideTwoIntegers {

    /**
     * a ^ b => 无进位相加
     * (a & b) << 1 => 进位信息
     */
    public static int add(int a, int b) {
        int sum = a;
        // 需要循环计算，直到进位信息等于0
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * n 的相反数   =>  n取反 +1
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }

    /**
     * a 和 b的相反数相加
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 乘法实现
     */
    public static int multi(int a, int b) {
        int res = 0;
        //
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     * 除实现
     * <p>
     * b向左移多少位但是又不超过a，那么向左移了几位，在答案中设置为1
     * 然后 a 减去 b位移之后的结果 记为 a`
     * b向左移多少位但是又不超过a`，那么向左移了几位，在答案中设置为1
     * 然后a`减去 b位移之后的结果 记为 a``
     * <p>
     * 重复
     * <p>
     * a / b
     * <p>
     * 具体实现和描述不同
     *
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        // 先转成正的，以绝对值的形式出现
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        // x向右移动31位，能不能减
        // x向右移动30位，能不能减
        for (int i = 31; i > negNum(1); i = minus(i, 1)) {
            // 如果x向右移动i位，如果大于y
            if ((x >> i) >= y) {
                // 1 向左移动 i 位，和res 或一下
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        // 如果a，b符号不同，取反
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    /**
     * 根据 除数 和 被除数 是不是系统最小 有几种可能性
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static int divide(int dividend, int divisor) {
        // 如果除数是系统最小，那么
        // 如果被除数是系统最小，直接返回1
        // 如果被除数不是系统最小，返回0
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        // 如果被除数是系统最小，但是除数不是系统最小
        // 计算逻辑需要看课
        if (dividend == Integer.MIN_VALUE) {
            // 如果除数是-1.溢出，直接返回系统最大
            if (divisor == negNum(1)) {
                return Integer.MAX_VALUE;
            }
            // 被除数：系统最小值 + 1
            // 然后除以除数
            int res = div(add(dividend, 1), divisor);
            // 需要补偿
            // 结果 + （ （被除数 - 结果 * 除数） / 除数）
            return add(res, div(minus(dividend, multi(res, divisor)), divisor));
        }

        // dividend不是系统最小，divisor也不是系统最小
        return div(dividend, divisor);
    }
    // div(a,b) a和b都不能是系统最小

    // 现场福利函数
    public static String printNumBinary(int num) {
        StringBuilder builder = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            builder.append(((num >> i) & 1) == 0 ? '0' : '1');
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int num = -1;
        System.out.println(printNumBinary(num));
    }

}
