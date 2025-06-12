package main.java.com.vsredshift.kyu3;

import java.util.Arrays;

public class Spiralizor {

    public static int[][] spiralize(int N) {
        int[][] matrix = new int[N][N];
        int top = 0, bottom = N - 1, left = 0, right = N - 1;
        int direction = 0; // 0: right, 1: down, 2: left, 3: up

        while (top <= bottom && left <= right) {
            // Fill top row (left to right)
            if (direction == 0) {
                for (int i = left; i <= right; i++) {
                    matrix[top][i] = 1;
                }
                top++;
                if (left != 0) {
                    left++;
                }
            }
            // Fill right column (top to bottom)
            else if (direction == 1) {
                for (int i = top; i <= bottom; i++) {
                    matrix[i][right] = 1;
                }
                right--;
                top++;
            }
            // Fill bottom row (right to left)
            else if (direction == 2) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = 1;
                }
                bottom--;
                right--;
            }
            // Fill left column (bottom to top)
            else if (direction == 3) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = 1;
                }
                left++;
                bottom--;
            }
            direction = (direction + 1) % 4; // Cycle directions
        }
        return matrix;
    }

    public static void main(String[] args) {
        spiralize(8);
    }



    // Alternative solution
    public static int[][] spiralizeV2(int size) {
        if(size <= 0) return null;
        int[][] spiral = new int[size][size];
        int minCol = 0;
        int maxCol = size-1;
        int minRow = 0;
        int maxRow = size-1;

        for (int i = 0; i < spiral.length; i++) {
            for (int j = 0; j < spiral.length; j++) spiral[i][j] = 0;
        }

        while (minRow <= maxRow){
            for (int i = minCol; i <= maxCol; i++) spiral[minRow][i] = 1;

            for (int i = minRow; i <= maxRow; i++) spiral[i][maxCol] = 1;

            if(minCol != 0) minCol+=1;
            if(maxRow-1 == minRow) break;

            for (int i = maxCol-1; i >= minCol; i--) spiral[maxRow][i] = 1;

            for (int i = maxRow-1; i >= minRow+2; i--) spiral[i][minCol] = 1;

            minCol+=1;
            minRow+=2;
            maxCol-=2;
            maxRow-=2;
        }
        return spiral;
    }

}
