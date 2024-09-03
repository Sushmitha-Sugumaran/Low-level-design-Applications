import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicToeTac t=new TicToeTac();

        HumanPlayer p1=new HumanPlayer("Akila",'o');
        AIPlayer p2=new AIPlayer("Aiin",'x');
        Player cp ;
        cp= p1;
        while (true){
            System.out.println("It's "+ cp.name+" turn");
            cp.makeMove();
            TicToeTac.display();
            if (TicToeTac.checkColWin() || TicToeTac.checkRowWin()
            ||TicToeTac.checkDiogWin()){
                System.out.println(cp.name+" has  won...");
                break;
            } else if (TicToeTac.checkdraw()) {
                System.out.println("match draw");
                break;
            }
            else {
                if (cp ==p1){
                    cp =p2;
                }
                else {
                    cp =p1;
                }
            }
        }
    }
}