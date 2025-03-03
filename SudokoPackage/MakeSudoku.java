package SudokoPackage;

import java.util.Random;

public class MakeSudoku {
    private static final int size = 9;
    private Board board;

    public MakeSudoku(Board board){
        this.board=board;
    }

    public boolean solveSudoku() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.isEmptyCell(row,col)) {
                    for (int num = 1; num <= 9; num++) {
                        if (board.isValid(row, col, num)) {
                            board.setCell(row,col,num);
                            if (solveSudoku()) {
                                return true;
                            }
                            board.setCell(row,col,0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void generateSudoku(int gameMood) {
        fillDiagonalBoxes();
        solveSudoku();
        removeNumbers(gameMood);
    }

    private void fillBox(int row, int col) {
        Random random = new Random();
        boolean[] used = new boolean[size + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = random.nextInt(size) + 1;
                } while (used[num]);
                used[num] = true;
                board.setCell(row+1,col+1,num);
            }
        }
    }

    public void fillDiagonalBoxes() {
        for (int i = 0; i < size; i += 3)
            fillBox(i, i);
    }

    public void removeNumbers(int count) {
        Random random = new Random();
        while (count > 0) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!board.isEmptyCell(row,col)) {
                board.setCell(row,col,0);
                count--;
            }
        }
    }
}
