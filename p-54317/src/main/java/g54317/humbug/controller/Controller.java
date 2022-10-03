package g54317.humbug.controller;

import g54317.humbug.view.text.InterfaceView;
import g54317.humbug.model.*;

/**
 * The controller class start thelevel and tell if a level is level is over or
 * not.
 *
 * @author Gardeur Nicolas 54317
 */
public class Controller {

    /**
     * Private and final attributs.
     */
    private final Model game;
    private final InterfaceView view;

    /**
     * Constructor of game and view.
     *
     * @param game Model
     * @param view InterfaceView
     */
    public Controller(Model game, InterfaceView view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Start the game and question for play.
     *
     * @param numLevel int
     */
    public void startGame(int numLevel) {
        while ( numLevel <= 100) {
            Position position;
            Direction direction;
            System.out.println("Bienvenue dans le niveau " +numLevel+" de humbug");

            try {
                game.startLevel(numLevel);
                do {

                    view.displayBoard(game.getBoard(), game.getAnimals());
                    view.displayRemainingMoves(game.getRemainingMoves());
                    position = view.askPosition();
                     while (!positionValid(position, game.getAnimals())) {
                        view.displayError("La position n'existe pas");
                        position = view.askPosition();
                    }
                    direction = view.askDirection();
                    game.move(position, direction);
                } while (game.getLevelStatus() != LevelStatus.WIN);
                numLevel++;

            } catch (Exception e) {
                view.displayError("Erreur : " + e.getMessage());
            }
        }
    }
     /**
     * Check position in board is valid.
     *
     * @param position position on board
     * @param animals array of animals
     * @return boolean position
     */
    private boolean positionValid(Position position, Animal... animals) {
        int z = 0;
        boolean positionValide = false;
        for(int i =0;i<animals.length && !positionValide;i++){
            if (animals[z].getPositionOnBoard().equals(position)) {
                positionValide = true;
            }
            z++;
        }
        return positionValide;
    }
}
