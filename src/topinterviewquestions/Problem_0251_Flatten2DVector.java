package topinterviewquestions;

/**
 * 结构设计题
 */
public class Problem_0251_Flatten2DVector {

    public static class Vector2D {
        private int[][] matrix;
        private int row;
        private int col;
        // 有没有使用过
        private boolean curUse;

        public Vector2D(int[][] v) {
            matrix = v;
            row = 0;
            col = -1;
            curUse = true;
            hasNext();
        }

        public int next() {
            int ans = matrix[row][col];
            curUse = true;
            hasNext();
            return ans;
        }

        public boolean hasNext() {
            // 越界了¬
            if (row == matrix.length) {
                return false;
            }
            // 目前的数还没有使用过
            if (!curUse) {
                return true;
            }

            // (row，col)用过了，获取新的[row, col]
            if (col < matrix[row].length - 1) {
                col++;
            } else {
                col = 0;
                // 避免空行
                do {
                    row++;
                } while (row < matrix.length && matrix[row].length == 0);
            }


            // 新的[row, col]如果有效
            if (row != matrix.length) {
                curUse = false;
                return true;
            } else {
                return false;
            }
        }

    }

}
