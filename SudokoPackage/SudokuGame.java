package SudokoPackage;

import java.io.IOException;
import java.util.Scanner;

public class SudokuGame {
    private static final int size = 9;
    private Board board;
    private MakeSudoku generator;
    private boolean gameRunning;
    private int gameMood;
    private Scanner scanner;

    public SudokuGame(){
        board=new Board();
        generator=new MakeSudoku(board);
        gameRunning=true;
        scanner=new Scanner(System.in);
    }

    public void start() throws IOException {
        WelcomeMessage();
        selectGameMood();
        generator.generateSudoku(gameMood);
        playGame();
        scanner.close();

    }
    public void WelcomeMessage() throws IOException {

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
    public void selectGameMood(){
    int choice;
        System.out.print(" Game mood: \n" + " 1.EASY.\n" + " 2.NORMAL\n" + " 3.HARD.\n" + " Enter your choice : ");
    choice = scanner.nextInt();
        System.out.println();
        while (choice < 1 || choice > 3) {
        System.out.print(" Enter the number between 1_3 : ");
        choice = scanner.nextInt();
        }
        switch (choice){
            case 1: gameMood=21; break;
            case 2: gameMood=41; break;
            case 3: gameMood=61; break;
        }
    }
    public void playGame(){

    while (gameRunning) {

        board.printBoard();

        int row, col, num;
        System.out.print("\n row (1_9): ");
        row = scanner.nextInt() - 1;
        System.out.print(" col (1_9): ");
        col = scanner.nextInt() - 1;
        System.out.print(" number (1_9): ");
        num = scanner.nextInt();
        if ((row >= 0 && row < size) && (col >= 0 && col < size) && (num > 0 && num < 10)) {
            if (board.isEmptyCell(row,col) && board.isValid(row, col, num)) {
                board.setCell(row,col,num);
            } else
                System.out.println("Invalid move! try again. \n");
        } else
            System.out.println(" Invalid input! numbers must be betweem (1_9). \n");
        if (board.isSolved()) {
            gameRunning = false;
            board.printBoard();
            System.out.println("\n YESSSSSSSSS!!!! ");
            System.out.println(" You completed the SUSOKU =) ");
        }
    }
        scanner.close();
    }
}
