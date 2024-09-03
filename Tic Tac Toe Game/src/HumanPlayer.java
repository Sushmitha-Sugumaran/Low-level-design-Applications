import java.util.Scanner;

public class HumanPlayer extends Player{
    String name;
    char mark;

    HumanPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    public void makeMove() {
        Scanner scan=new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("enter row and col to move");
            row= scan.nextInt();
            col= scan.nextInt();
        }
        while (!isValidMove(row,col));
        TicToeTac.placeMove(row,col,super.mark);
    }



}
