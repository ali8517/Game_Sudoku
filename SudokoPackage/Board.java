package SudokoPackage;

import java.io.IOException;

public class Board {
    private static final int size = 9;
    private int[][] board;

    public Board(){
        board=new int[size][size];
    }

    public int[][] getBoard(){
        return board;
    }

    public void setCell(int row, int col , int num){
        board[row][col]=num;
    }

    public boolean isEmptyCell(int row, int col){
        return board[row][col]==0;
    }

    public boolean isValid(int row, int col, int num) {

        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = (row / 3) * 3, startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
        return true;
    }

    public boolean isSolved(){
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (board[i][j]==0)
                    return false;
        return true;
    }

    public void printBoard() {

        System.out.println("   " + "1 2 3   4 5 6   7 8 9");
        System.out.println("  +---------------------+");

        for (int i = 0; i < size; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println(" | ------+-------+------ |");

            for (int j = 0; j < size; j++) {
                if (j == 0)
                    System.out.print(i + 1 + "| ");

                if (j % 3 == 0 && j != 0)
                    System.out.print("| ");
                System.out.print(board[i][j] == 0 ? ". " : board[i][j] + " ");

                if (j == 8)
                    System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("  +---------------------+");
    }

}
