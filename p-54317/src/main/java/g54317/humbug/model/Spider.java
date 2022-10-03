package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature
 *
 * @author Gardeur Nicolas 54317
 */
public class Spider extends Animal {

    public Spider() {
    }

    /**
     * Animal on the board call spyder.
     *
     * @param position Position.
     */
    public Spider(Position position) {
        super(position);
    }

    /**
     * All possible movements for the spider.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return position of the spider
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        Position move = deplacementSpider(board, position, direction, animals);
        boolean isOccupied = true;

        for (int i = 0; i < animals.length && isOccupied; i++) {
            if (animals[i].getPositionOnBoard().equals(position.next(direction))) {
                isOccupied = false;
            }
        }
        if (move == null) {
            super.setPositionOnBoard(null);
            return null;
        }

        if (board.isInside(move)) {
            if (board.getSquareType(move)
                    == SquareType.GRASS && isOccupied) {
                super.setPositionOnBoard(move);
                return move;
            } else if (board.getSquareType(move) == SquareType.STAR) {
                super.setOnStar(true);
                board.GrassSets(move);
                super.setPositionOnBoard(move);
                return super.getPositionOnBoard();
            }

        }
        if (board.isInside(move) && !isOccupied) {
            return position;
        }
        super.setPositionOnBoard(null);
        return null;
    }

    /**
     * Deplacement of the spider with wall.
     *
     * @param board Board
     * @param position Position
     * @param direction Direction
     * @param animals Animal...
     * @return position of the snail if wall are in the board
     */
    public Position deplacementSpider(Board board, Position position,
            Direction direction, Animal... animals) {
        boolean animalIsInBoard = false;

        if (direction == Direction.EAST || direction == Direction.WEST) {
            for (int i = 0; i < board.getNbColumn(); i++) {
                for (int j = 0; j < animals.length && !animalIsInBoard; j++) {
                    if (animals[j].getPositionOnBoard().equals(position.next(direction)) && !animals[j].isOnStar()) {
                        animalIsInBoard = true;
                        break;
                    }
                }
                if (animalIsInBoard) {
                    return position;
                }
                if (wallDirection(board, direction, position)) {
                    return position;
                }
                if (wallDirectionOpposite(board, direction, position)) {
                    return position;
                }
                position = position.next(direction);
            }
        } else if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            for (int i = 0; i < board.getNbRow(); i++) {

                for (int j = 0; j < animals.length && !animalIsInBoard; j++) {
                    if (animals[j].getPositionOnBoard().equals(position.next(direction))) {
                        animalIsInBoard = true;
                        break;
                    }
                }
                if (wallDirection(board, direction, position)) {
                    return position;
                }
                if (animalIsInBoard) {
                    return position;
                }

                if (wallDirectionOpposite(board, direction, position)) {
                    return position;
                }
                position = position.next(direction);
            }
        }
        return null;
    }

    /**
     * Check if there is a wall in the actual position.
     *
     * @param board Board
     * @param direction Direction
     * @param position Position
     * @return boolean if there is a wall or not
     */
    private boolean wallDirection(Board board, Direction direction, Position position) {
        if (board.isInside(position)) {
            return super.isNextWall(board, position, direction);
        }
        return false;
    }

    /**
     * Check the wall opposite of the direction in the next position.
     *
     * @param board Board
     * @param direction Direction
     * @param position Position
     * @return boolean if there is a wall or not.
     */
    private boolean wallDirectionOpposite(Board board, Direction direction, Position position) {
        if (board.isInside(position.next(direction))) {
            return isNextWallOpposite(board, position, direction);
        }
        return false;
    }

}
