package TemaPOO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Alege modul de joc");
        System.out.println("1 - GUI");
        System.out.println("2 - PLayText");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Game g = Game.getInstance();
        g.run();
        g.story();
        if ( i == 1) {
            Choose c = new Choose(g.accounts, Game.forest, Game.inamic, Game.finish);
        }
        else {
            if ( i == 2) {
                g.playtext();
            }
            else {
                throw new InvalidCommandException();
            }
        }

    }
}
