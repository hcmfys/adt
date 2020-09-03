package org.springbus;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MatrixRotate {


    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        MatrixRotate t = new MatrixRotate();
        t.rotate(a);
        t.printData(a);

    }

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int i = matrix.length;
        int j = matrix[0].length;
        /**
         *     [1,2,3],
         *     [4,5,6],
         *     [7,8,9]
         *  --------------------
         *     [7,4,1],
         *     [8,5,2],
         *     [9,6,3]
         */
        List<List<Integer>> arr = new ArrayList<>();
        for (int q = 0; q < j; q++) {
            List<Integer> it = new ArrayList<>();
            for (int k = 0; k < i; k++) {
                it.add(0, matrix[k][q]);
            }
            arr.add(it);

        }

        for (int k = 0; k < j; k++) {
            for (int q = 0; q < i; q++) {
                matrix[k][q] = arr.get(k).get(q);
            }
        }
    }

    public void printData(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println("");
        }
    }


}
