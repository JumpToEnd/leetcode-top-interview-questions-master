package topinterviewquestions;

import java.util.HashMap;

public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 来一张表缓存中序数组中所有 数据的 索引位置
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 调用递归函数
        return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    /**
     * 给定
     * 一个先序遍历数组 pre， 在[L1...R1]位置
     * 一个中序遍历数组 in，在[L2...R2]位置
     * 上建立一个二叉树，返回头结点
     *
     * @param pre 先序遍历数组
     * @param L1  先序遍历的L位置
     * @param R1  先序遍历的R位置
     * @param in  中序遍历数组
     * @param L2  中序遍历的L位置
     * @param R2  中序遍历的R位置
     * @param map 缓存
     */
    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
        // 越界情况，需要重新分析
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int findIndex = map.get(pre[L1]);
        head.left = f(pre, L1 + 1, L1 + findIndex - L2, in, L2, findIndex - 1, map);
        head.right = f(pre, L1 + findIndex - L2 + 1, R1, in, findIndex + 1, R2, map);
        return head;
    }

}
