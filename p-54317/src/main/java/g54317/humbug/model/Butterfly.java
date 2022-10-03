package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature.
 *
 * @author Gardeur Nicolas 54317
 */
public class Butterfly extends Animal {

    /**
     * Animal on the board call butterfly.
     *
     * @param position Position
     */
    public Butterfly(Position position) {
        super(position);
    }

    /**
     * Constructor whitout parameter.
     */
    public Butterfly() {
    }

    /**
     * All possible movements for the butterfly.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return position of the butterfly
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
       Position position = getPositionOnBoard().next(direction).next(direction).next(direction);
       return ifFly(board, position, direction, animals);
    }

    /**
     * Check if the butterlfy is in the board in the next direction.
     *
     * @param board Board
     * @param direction Direction
     * @param position Position
     * @return boolean inside or not
     */
    private boolean isInside(Board board, Direction direction, Position position) {
        boolean a = board.isInside(position);
        return a;
    }

}
