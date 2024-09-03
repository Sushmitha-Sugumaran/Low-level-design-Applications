import java.util.Random;

public class AIPlayer extends Player{
    String name;
    char mark;
    AIPlayer(String name,char mark){
        super(name, mark);
    }
    @Override
   public void makeMove() {
        int row;
        int col;
        do {
            Random r=new Random();
            System.out.println("enter row and col to move");
            row= r.nextInt(3);
            col= r.nextInt(3);
        }
        while (!isValidMove(row,col));
        TicToeTac.placeMove(row,col,super.mark);
    }

}
