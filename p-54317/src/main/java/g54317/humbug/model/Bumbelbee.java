package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature.
 *
 * @author Gardeur Nicolas 54317
 */
public class Bumbelbee extends Animal {

    /**
     * Animal on the board call bumbelbee.
     *
     * @param position Position
     */
    public Bumbelbee(Position position) {
        super(position);
    }

    /**
     * Constructor whitout parameter.
     */
    public Bumbelbee() {
    }

    /**
     * All possible movements for the bumbelbee.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return position of the bumbelbee
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard().next(direction).next(direction);
        return ifFly(board,position, direction, animals);
        
    }
}
