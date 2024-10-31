package TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            initializeBoard();
            currentPlayer = 'X';
            playGame();
        } while (playAgain());
    }

    // Initializes the board with empty spaces
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Main game loop
    private static void playGame() {
        boolean gameWon = false;
        while (!gameWon && !isBoardFull()) {
            printBoard();
            playerMove();
            gameWon = checkWinner();
            if (!gameWon) {
                togglePlayer();
            }
        }
        printBoard();
        if (gameWon) {
            System.out.println("Player " + currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    // Toggles between players X and O
    private static void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Prints the current state of the board
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Manages player move input with validation
    private static void playerMove() {
        int row = -1, col = -1;
        while (true) {
            try {
                System.out.println("Player " + currentPlayer + ", enter your move (row and column: 1-3): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;

                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter numbers only (1-3 for row and column).");
                scanner.next();  // Clear the invalid input
            }
        }
    }

    // Checks for a win condition
    private static boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer)
                return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)
                return true;
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    // Checks if the board is full (draw)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Prompts the user to play again
    private static boolean playAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String answer = scanner.next().trim().toLowerCase();
        return answer.equals("yes");
    }
}
