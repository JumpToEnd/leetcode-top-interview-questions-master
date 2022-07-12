package topinterviewquestions;

/**
 * 二叉树递归套路分析：
 * <p>
 * 可能性1：不过x节点
 * 1. 左侧的最大路径和 maxPathSum
 * 2. 右侧的最大路径和 maxPathSum
 * <p>
 * Math.max(左侧最大路径和，右侧最大路径和) 就是最终结果
 * <p>
 * 可能性2：过x节点
 * 1. 只要x节点自己
 * 2. 只向左侧扎  =>  x节点 + 左树必须从头结点出发的最大路径和
 * 3. 只向右侧扎  =>  x节点 + 右树必须从头结点出发的最大路径和
 * 4. 向两侧扎
 */
public class Problem_0124_BinaryTreeMaximumPathSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    public static class Info {
        // 有可能经过头节点
        public int maxPathSum;
        // 必须从头节点出发
        public int maxPathSumFromHead;

        public Info(int path, int head) {
            maxPathSum = path;
            maxPathSumFromHead = head;
        }
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.maxPathSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.maxPathSum;
        }
        int p3 = x.val;

        // 可能性4 向左扎
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.val + leftInfo.maxPathSumFromHead;
        }

        // 可能性5  向右扎
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.val + rightInfo.maxPathSumFromHead;
        }

        // 可能性6 向两边扎
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = x.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead;
        }
        int maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
        int maxSumFromHead = Math.max(p3, Math.max(p4, p5));
        return new Info(maxSum, maxSumFromHead);
    }

}
