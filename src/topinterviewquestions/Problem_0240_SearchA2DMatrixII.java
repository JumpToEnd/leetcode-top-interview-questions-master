package topinterviewquestions;

/**
 * 每一行有序
 * 每一列也有序
 * <p>
 * 从右上角开始找
 * 如果当前位置[i][j]的值 小于 target，那么左侧的数都不可能，来到下一行
 * 如果当前位置[i][j]的值 大于 target，那么下面的数都不可能，来到前一列
 */
public class Problem_0240_SearchA2DMatrixII {

    public static boolean searchMatrix(int[][] m, int target) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        int N = m.length;
        int M = m[0].length;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            if (m[row][col] > target) {
                col--;
            } else if (m[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

}
