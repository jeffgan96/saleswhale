package com.example.restservice;

import org.springframework.stereotype.Component;

@Component
public class DfsSolver implements Solver {

    public boolean doesWordExistOnBoard(Board board, String word) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //find first match
                if (board.compareTileValue(i, j, word.charAt(0))) {
                    return search(board, i, j, word);
                }
            }
        }
        return false;
    }

    private boolean search(Board board, int row, int col, String word, int index, boolean [][] visited) {
        if (index >= word.length()) {
            return false;
        }

        String check = String.valueOf(word.charAt(index));

        //terminating condition
        if(board.compareTileValue(row, col, check) && index == word.length() - 1) {
            return true;
        }

        if (board.compareTileValue(row, col, check)) {
            visited[row][col] = true;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    int nextRow = row + i;
                    int nextCol = col + j;

                    if (nextRow < 0 || nextCol < 0) {
                        continue;
                    }

                    if (search(board, nextRow, nextCol, word, index + 1, visited)) {
                        return true;
                    }

                }
            }

        }
        return false;
    }

    private boolean search(Board board, int row, int col, String word) {
        boolean[][] visited = new boolean[Board.ROWS_COUNT][Board.COLS_COUNT]; //defaulted to false
        return search(board, row, col, word, 0, visited);
    }

}
