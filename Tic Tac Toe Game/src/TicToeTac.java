public class TicToeTac {
    static char[][] board;
    public TicToeTac() {
        board = new char[3][3];
        initBoard();
    }

    public static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] =' ';
            }
        }
    }
    public static void display(){
        System.out.println("-------------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|  ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+"  |  ");
            }
            System.out.println();
            System.out.println("-------------------");
        }
    }
    public static void placeMove(int row,int col,char mark){
        if (row>=0 && row<=2 && col>=0 && col<=2) {
            board[row][col] = mark;
        }
        else {
            System.out.println("Invalid Position...");
        }
    }
    public static boolean checkColWin(){
        for (int i = 0; i < board.length; i++) {
          if (board[0][i]!=' ' && board[0][i]==board[1][i] && board[1][i]== board[2][i]) {
              return true;
          }
        }
        return false;
    }
    public static boolean checkRowWin(){
        for (int i = 0; i < board.length; i++) {
            if (board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]== board[i][2]) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkDiogWin(){
        if((board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]) || (board[0][2]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])){
            return true;
        }
        return false;
    }
    public static boolean checkdraw(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]==' '){
                    return false;
                }
            }
        }
        return true;
    }
}
