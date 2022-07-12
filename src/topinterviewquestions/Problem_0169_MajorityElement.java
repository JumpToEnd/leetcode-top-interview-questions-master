package topinterviewquestions;

/**
 * 出现超过 2/n次 的数
 * <p>
 * 思路：
 * 一次删掉两个不同的数 剩下的就是 解
 */
public class Problem_0169_MajorityElement {

    public static int majorityElement(int[] nums) {
        // 靶子
        int cand = 0;
        // 靶子的血量
        int HP = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前靶子的血量为0，把当前值设置为靶子，血量为1
            if (HP == 0) {
                cand = nums[i];
                HP = 1;
            }
            // 如果当前的值和靶子相同，血量+1
            else if (nums[i] == cand) {
                HP++;
            }
            // 如果当前的值和靶子不同，血量-1
            else {
                HP--;
            }
        }
        return cand;
    }

}
