package tictactoe;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void dispBoard() {
        System.out.println("   0   1   2");
        System.out.println(" -------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println(" -------------");
        }
    }

    static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (board[row][col] == ' ') {
                board[row][col] = mark;
            } else {
                System.out.println("Position already taken!");
            }
        } else {
            System.out.println("Invalid Position");
        }
    }

    static boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' &&
                board[0][j] == board[1][j] &&
                board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin() {
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return true;
        }

        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }

    static boolean checkDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

abstract class player {
    String name;
    char mark;
    abstract void makeMove();

    boolean isvalidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (TicTacToe.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

class humanPlayer extends player {
    static Scanner scan = new Scanner(System.in);

    humanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        int row;
        int col;
        do {
            System.out.println("Enter row and column (0, 1, 2):");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isvalidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }
}

class AIPlayer extends player {
    Random r = new Random();

    AIPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        int row;
        int col;
        do {
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isvalidMove(row, col));
        TicTacToe.placeMark(row, col, mark);
    }
}

public class Launch{
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        humanPlayer p1 = new humanPlayer("bob", 'x');
        AIPlayer p2 = new AIPlayer("TIA", 'o');
        player cp;
        cp = p1;

        while (true) {
            System.out.println(cp.name + "'s turn:");
            cp.makeMove();
            TicTacToe.dispBoard();

            if (TicTacToe.checkRowWin() ||
                TicTacToe.checkColWin() ||
                TicTacToe.checkDiagWin()) {
                System.out.println(cp.name + " has won!");
                break;
            } else if (TicTacToe.checkDraw()) {
                System.out.println("Game is a draw!");
                break;
            } else {
                cp = (cp == p1) ? p2 : p1;
            }
        }
    }
}







































