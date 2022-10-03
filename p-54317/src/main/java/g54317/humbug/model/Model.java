package g54317.humbug.model;

/**
 * Interface implement modificate method
 *
 * @author Gardeur Nicolas 54317
 */
public interface Model {

    /**
     * Simple getter of Board.
     *
     * @return
     */
    Board getBoard();

    /**
     * Simple getter of Animals.
     *
     * @return
     */
    Animal[] getAnimals();

    /**
     * Attributs getRemainingMoves.
     *
     * @return
     */
    int getRemainingMoves();

    /**
     * Start the level and initial the board and animals.
     *
     * @param n
     */
    void startLevel(int n);

    /**
     * Check if the level is over or not.
     *
     * @return boolean level
     */
    LevelStatus getLevelStatus();

    /**
     *
     * @param position Position
     * @param direction Direction
     */
    void move(Position position, Direction direction);
}
