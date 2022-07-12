package topinterviewquestions;

import java.util.LinkedList;

/**
 * 227题加强版
 * <p>
 * 所有的使用括号来计算优先级的都要用这个递归套路
 * <p>
 * 递归 f(str, i)
 * 从i位置开始到结尾或者第一个右括号为止
 * <p>
 * 返回[计算结果，计算到了哪里]
 */
public class Problem_0772_BasicCalculatorIII {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    /**
     * 从str的i位置开始计算，到终止位置或者右括号为止
     *
     * @param str 字符串
     * @param i   当前位置
     * @return 长度为2的数组，[计算结果，计算到了哪里]
     */
    public static int[] f(char[] str, int i) {
        // 栈
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸str
        // 越界过着遇到右括号 终止
        while (i < str.length && str[i] != ')') {
            // 空字符串跳过
            if (str[i] == ' ') {
                i++;
                continue;
            }

            // 如果遇到的是数字
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            }
            // 遇到了运算符
            else if (str[i] != '(') {
                // que 接受的是str的东西，cur是一个整型
                addNum(que, cur);

                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            }
            // 遇到了左括号
            else {
                // 调用子过程
                bra = f(str, i + 1);
                // 获取结果
                cur = bra[0];
                // 位置+1
                i = bra[1] + 1;
            }
        }

        // 计算
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    /**
     * 如果栈顶是 + 或者 -，直接入栈
     * 如果栈顶是 * 或者 /，计算完之后再入栈
     *
     * @param que 栈
     * @param num 操作数
     */
    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    /**
     * 获取计算结果
     *
     * @param que 栈
     * @return
     */
    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}
