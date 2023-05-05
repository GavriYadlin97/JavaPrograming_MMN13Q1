package com.example.maman13q1;

public class SudokuBoard {

    private static final int SIZE = 9;
    private static final int BLOCK_SIZE = 3;
    private int[][] board;

    private int numbersOnBoard;

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        numbersOnBoard = 0;
    }

    public void setCell(int row, int column, int num) throws IllegalArgumentException {
        if ((checkNum(num) && checkRow(row, num) && checkColumn(column, num) && checkBlock(row, column, num)) || board[row][column] == num) {
            if (board[row][column] == 0)
                numbersOnBoard++;
            board[row][column] = num;
        } else
            throw new IllegalArgumentException();
    }

    public boolean checkNum(int num) {
        return 1 <= num && num <= SIZE;
    }

    public boolean checkRow(int row, int num) {
        if (row >= SIZE || row < 0)
            return false;
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num)
                return false;
        }
        return true;
    }

    public boolean checkColumn(int column, int num) {
        if (column >= SIZE || column < 0)
            return false;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][column] == num)
                return false;
        }
        return true;
    }

    public boolean checkBlock(int row, int column, int num) {
        int blockMinRow = (row / BLOCK_SIZE) * BLOCK_SIZE; //taking advantage of integer division property: behaves like Math.floor
        int blockMaxRow = blockMinRow + BLOCK_SIZE;
        int blockMinColumn = (column / BLOCK_SIZE) * BLOCK_SIZE;
        int blockMaxColumn = blockMinColumn + BLOCK_SIZE;
        for (int i = blockMinRow; i < blockMaxRow; i++) {
            for (int j = blockMinColumn; j < blockMaxColumn; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }
        return true;
    }

    public boolean isSet(int row, int column) {
        return board[row][column] != 0;
    }

    public boolean isVictory() {
        return numbersOnBoard == Math.pow(SIZE, 2);
    }
}
