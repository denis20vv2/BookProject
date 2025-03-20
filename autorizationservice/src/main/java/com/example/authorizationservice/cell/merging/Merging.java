package com.example.authorizationservice.cell.merging;

import com.example.authorizationservice.page.domain.Data;
import com.example.authorizationservice.user.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Merging {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static int[][] createMatrix(List<String> keys) {
        int maxRow = 0;
        int maxCol = 0;

        for (String key : keys) {
            int[] coordinates = parseKey(key);
            int row = coordinates[0];
            int col = coordinates[1];

            maxRow = Math.max(maxRow, row);
            maxCol = Math.max(maxCol, col);
        }

        return new int[maxRow + 1][maxCol + 1];
    }

    public static int[][] fillMatrix(int[][] matrix, List<String> keysMerging) {
        for (String key : keysMerging) {
            int[] coordinates = parseKey(key);
            int row = coordinates[0];
            int col = coordinates[1];

            matrix[row][col] = 1;
        }
        return matrix;
    }

    // Метод для парсинга
    private static int[] parseKey(String key) {
        String[] parts = key.substring(1).split(":");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }
    /*public static void ConvertToNumeric(List<String> keys){

         // input = "C1:12";
         String[] parts = key.substring(1).split(":");

         int firstNumber = Integer.parseInt(parts[0]);
         int secondNumber = Integer.parseInt(parts[1]);

        logger.error("Первое число:" + firstNumber);
        logger.error("Второе число: " + secondNumber);
    }*/
    public static boolean canMerge(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return false;
        int m = matrix[0].length;

        int minRow = n, maxRow = -1, minCol = m, maxCol = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        if (minRow == n || maxRow == -1 || minCol == m || maxCol == -1) return false;

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

 }
