package g54317.humbug.view.text;

import g54317.humbug.model.Board;
import g54317.humbug.model.Direction;
import g54317.humbug.model.Position;
import g54317.humbug.model.Animal;

/**
 * InterfaceView.
 *
 * @author Gardeur Nicolas 54317
 */
public interface InterfaceView {

    /**
     * Display the board of the game.
     *
     * @param board Board
     * @param animals Animal...
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Display error message.
     *
     * @param message String
     */
    void displayError(String message);

    /**
     * Ask next position at the players.
     *
     * @return position
     */
    Position askPosition();

    /**
     * Ask next direction at the players.
     *
     * @return direction
     */
    Direction askDirection();

    /**
     * Display the remaining movemengt possible
     *
     * @param RemainingMoves int
     */
    void displayRemainingMoves(int RemainingMoves);
}
