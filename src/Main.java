import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int size = 9;
    private static int[][] board = new int[size][size];
    private static boolean gameRunning = true;
    private static int gameMood;

    public static void WelcomeMessage() throws IOException {

        System.out.print("===========================================\n");
        System.out.print("          WELCOME TO SUDOKU! \n");
        System.out.print("===========================================\n");
        System.out.print("         Can you solve the table ?         \n");
        System.out.print("-------------------------------------------\n");
        System.out.print("           Press ENTER to start...         \n");
        System.out.print("===========================================\n");
        int ch;
        do {
            ch = System.in.read();
        } while (ch != '\n');
    }

    public static void gameMood(int choice) {


        switch (choice) {
            case 1:
                gameMood = 21;
                break;
            case 2:
                gameMood = 41;
                break;
            case 3:
                gameMood = 61;
                break;
        }
    }

    public static boolean solveSudoku() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku()) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void generateSudoku() {
        board = new int[size][size];
        fillDiagonalBoxes();
        solveSudoku();
        removeNumbers(gameMood);
    }

    private static void fillBox(int row, int col) {
        Random random = new Random();
        boolean[] used = new boolean[size + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = random.nextInt(size) + 1;
                } while (used[num]);
                used[num] = true;
                board[row + i][col + i] = num;
            }
        }
    }

    public static void fillDiagonalBoxes() {
        for (int i = 0; i < size; i += 3)
            fillBox(i, i);
    }

    public static void removeNumbers(int count) {
        Random random = new Random();
        while (count > 0) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }

    public static boolean isValid(int row, int col, int num) {
        if (board[row][col] != 0)
            return false;
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

    public static void printBoard() {

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

    public static boolean win() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (board[i][j] == 0 && !isValid(i, j, board[i][j]))
                    return false;
        return true;
    }

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        WelcomeMessage();
        int choice;
        System.out.print(" Game mood: \n" + " 1.EASY.\n" + " 2.NORMAL\n" + " 3.HARD.\n" + " Enter your choice : ");
        choice = input.nextInt();
        System.out.println();
        while (choice < 1 || choice > 3) {
            System.out.print(" Enter the number between 1_3 : ");
            choice = input.nextInt();
        }
        gameMood(choice);
        generateSudoku();

        while (gameRunning) {

            printBoard();
            int row, col, num;
            System.out.print("\n row (1_9): ");
            row = input.nextInt() - 1;
            System.out.print(" col (1_9): ");
            col = input.nextInt() - 1;
            System.out.print(" number (1_9): ");
            num = input.nextInt();
            if ((row >= 0 && row < size) && (col >= 0 && col < size) && (num > 0 && num < 10)) {
                if (board[row][col] == 0 && isValid(row, col, num)) {
                    board[row][col] = num;
                } else
                    System.out.println("Invalid move! try again. \n");
            } else
                System.out.println(" Invalid input! numbers must be betweem (1_9). \n");
            if (win()) {
                gameRunning = false;
                printBoard();
                System.out.println("\n YESSSSSSSSS!!!! ");
                System.out.println(" You completed the SUSOKU =) ");
            }
        }
        input.close();
    }
}