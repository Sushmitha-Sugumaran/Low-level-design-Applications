public abstract class Player {
    String name;
    char mark;
    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public abstract  void makeMove();

    public boolean isValidMove(int row,int col) {
        if(row>=0 && row<=2 && col>=0 && col<=2){
            if (TicToeTac.board[row][col]==' '){
                return  true;
            }
        }
        return false;
    }
}
