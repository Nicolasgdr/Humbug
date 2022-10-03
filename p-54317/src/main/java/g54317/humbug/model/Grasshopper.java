package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature.
 *
 * @author Gardeur Nicolas 54317
 */
public class Grasshopper extends Animal {

    /**
     * Animal on the board call grasshopper.
     *
     * @param position Position
     */
    public Grasshopper(Position position) {
        super(position);
    }

    /**
     * Constructor without parameter.
     */
    public Grasshopper() {
    }

    /**
     * All possible movements for the grasshopper.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return position of the grasshopper
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        var nextposition = animalNextPosi(board, direction, animals);
        if (nextposition == null) {
            super.setPositionOnBoard(null);
            return null;
        }
        if (isInside(board, direction, position)) {
            if (board.getSquareType(nextposition)
                    == SquareType.GRASS ) {
                super.setPositionOnBoard(nextposition);
                return nextposition;
            } else if (board.getSquareType(nextposition) == SquareType.STAR ) {
                super.setOnStar(true);
                board.GrassSets(nextposition);
                super.setPositionOnBoard(nextposition);
                return nextposition;
            }
        }
        return null;
    }

    /**
     * check if the grasshopper is in the board in the next direction
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
    /**
     * Look if an animal is in the next position.
     * @param board Board
     * @param direction Direction
     * @param animals Animal[]
     * @return instance ( a position)
     */
    private Position animalNextPosi(Board board, Direction direction, Animal... animals) {
        var instance = positionOnBoard.next(direction);
        if (isInside(board, direction, instance)) {
            while (!isFree(instance, animals) && isInside(board, direction, instance)) {
                instance = instance.next(direction);
            }
            return instance;
        } else {
           instance = null;
            
        }
        return instance;
    }
}
