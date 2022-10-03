package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature.
 *
 * @author Gardeur Nicolas 54317
 */
public class Snail extends Animal {

    /**
     * Constructor whitout parameters.
     */
    public Snail() {
    }

    /**
     * Animal on the board call snail.
     *
     * @param position Position
     */
    public Snail(Position position) {
        super(position);
    }

    /**
     * Check all possible movements for the snail and move.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return position of the snail
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        boolean isOccupied = false;
        for (int i = 0; i < animals.length && !isOccupied; i++) {
            if (animals[i].getPositionOnBoard().equals(position.next(direction))) {
                isOccupied = true;
            }
        }
        if (!isInside(board, direction, position) && lookWall(board, position, direction)) {
            super.setPositionOnBoard(position);
            return position;
        }
        if (isInside(board, direction, position)) {
            if (lookWall(board, position, direction) || lookWall(board, position.next(direction), direction.opposite())) {
                super.setPositionOnBoard(position);
                return position;
            } else if (board.getSquareType(position.next(direction))
                    == SquareType.GRASS && !isOccupied) {
                super.setPositionOnBoard(position.next(direction));
                return position.next(direction);
            } else if (board.getSquareType(position.next(direction)) == SquareType.STAR && !isOccupied) {
                super.setOnStar(true);
                super.setPositionOnBoard(position.next(direction));
                board.GrassSets(position.next(direction));
                return super.getPositionOnBoard();
            } else if (board.isInside(position.next(direction)) && isOccupied) {
                super.setPositionOnBoard(position);
                return position;
            }
        }
        super.setPositionOnBoard(null);
        return null;

    }

    /**
     * Check if the snail is in the board in the next direction.
     *
     * @param board Board
     * @param direction Direction
     * @param position Position
     * @return boolean inside or not
     */
    private boolean isInside(Board board, Direction direction, Position position) {
        boolean a = board.isInside(position.next(direction));
        return a;
    }

    /**
     * Check if there is a wall or not.
     *
     * @param board Board
     * @param position Position
     * @param direction Direction
     * @return boolean wall or not
     */
    private boolean lookWall(Board board, Position position, Direction direction) {
        if (isNextWall(board, position, direction)) {
            return true;
        }
        return false;
    }
}
