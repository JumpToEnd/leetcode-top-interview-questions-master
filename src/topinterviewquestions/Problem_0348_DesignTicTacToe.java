package topinterviewquestions;

/**
 *
 */
public class Problem_0348_DesignTicTacToe {

    class TicTacToe {
        private int[][] rows;
        private int[][] cols;
        private int[] leftUp;
        private int[] rightUp;
        private boolean[][] matrix;
        private int N;

        public TicTacToe(int n) {
            // 行
            rows = new int[n][3]; //0 1 2
            // 列
            cols = new int[n][3];
            // 左对角线
            leftUp = new int[3]; //  1 2
            // 右对角线
            rightUp = new int[3]; // 1 2
            // 记录下过的点
            matrix = new boolean[n][n];
            N = n;
        }

        public int move(int row, int col, int player) {
            if (matrix[row][col]) {
                return 0;
            }
            matrix[row][col] = true;
            rows[row][player]++;
            cols[col][player]++;
            // 左对角线
            if (row == col) {
                leftUp[player]++;
            }

            // 右对角线
            if (row + col == N - 1) {
                rightUp[player]++;
            }
            // 判断赢
            if (rows[row][player] == N || cols[col][player] == N || leftUp[player] == N || rightUp[player] == N) {
                return player;
            }
            return 0;
        }

    }

}
