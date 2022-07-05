package topinterviewquestions;

public class Problem_0048_RotateImage {

    public static void rotate(int[][] matrix) {
        // matrix.len == matrix[0].len
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;


        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        // 确定有多少个组
        int times = dC - tC;
        int tmp = 0;
        // 第i组的第一个数 m[tR][tC + i]
        // 第i组的第二个数 m[tR + i][dC]
        // 第i组的第三个数 m[dR][dC - i]
        // 第i组的第四个数 m[dR - i][tC]
        for (int i = 0; i != times; i++) {
            // 第i组的第一个数
            tmp = m[tR][tC + i];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
    }

}
