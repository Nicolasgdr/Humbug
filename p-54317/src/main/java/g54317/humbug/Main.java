package g54317.humbug;

import g54317.humbug.controller.Controller;
import g54317.humbug.view.text.View;
import g54317.humbug.model.Game;
import java.util.Scanner;

/**
 * Launch the game
 *
 * @author Gardeur Nicolas 54317
 */
public class Main {

    /**
     * Call the controller for play the game.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());

        int level = verifLevel();

        controller.startGame(level);
    }

    /**
     * Check if the level is between 1 and 100.
     *
     * @return numlevel
     */
    private static int verifLevel() {
        int numlevel;
        numlevel = lireEntier("entrer un niveau");

        while (numlevel <= 0 || numlevel > 100) {
            numlevel = lireEntier("entrer un niveau");
        }
        return numlevel;
    }

    /**
     * Robust scanner.
     *
     * @param message String
     * @return number enter
     */
    private static int lireEntier(String message) {
        Scanner clavier = new Scanner(System.in);
        System.out.println(message);
        while (!clavier.hasNextInt()) {
            clavier.next();
            System.out.println("Le nombre saisi n'est pas un entier.");
            System.out.println(message);
        }
        return clavier.nextInt();
    }

}
