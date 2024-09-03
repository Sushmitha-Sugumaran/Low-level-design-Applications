import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakeGaming {
    char[][] board;
    Queue<Node> snakeLen = new LinkedList<>();

    public SnakeGaming(int row, int col) {
        board = new char[row][col];
        initBoard();
        this.snakeLen.add(new Node(0, 0));
        this.board[0][0] = '.';  // Start position
        this.board[0][1] = 'X';
        this.board[2][4] = 'X';
        this.board[5][4] = 'X';
    }

    private void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '0';
            }
        }
    }

    public void startGame() {
        int row = 0, col = 0;
        while (true) {
            printSnakeBoard();
            System.out.println("Enter direction to move (U/D/L/R):");
            Scanner scan = new Scanner(System.in);
            char direction = scan.next().toUpperCase().charAt(0);

            switch (direction) {
                case 'U': row--; break;
                case 'D': row++; break;
                case 'L': col--; break;
                case 'R': col++; break;
                default:
                    System.out.println("Invalid direction. Use U, D, L, or R.");
                    continue;
            }

            if (!snakeMove(row, col)) {
                break;
            }
        }
    }

    private boolean snakeMove(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            System.out.println("Game Over... Snake hit the wall!");
            return false;
        }

        if (board[row][col] == '.') {
            System.out.println("Game Over... Snake bit itself!");
            return false;
        }

        snakeLen.add(new Node(row, col));

        if (board[row][col] != 'X') {
            Node node = snakeLen.poll();
            board[node.getRow()][node.getCol()] = '0';
        }

        board[row][col] = '.';
        return true;
    }

    private void printSnakeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}