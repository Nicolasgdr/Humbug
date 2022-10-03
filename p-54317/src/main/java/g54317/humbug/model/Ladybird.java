package g54317.humbug.model;

/**
 * Animal create for take the star on the game Move the animal with different
 * feature.
 *
 * @author Gardeur Nicolas 54317
 */
public class Ladybird extends Animal {

    /**
     * Animal on the board call ladybird.
     *
     * @param position Position
     */
    public Ladybird(Position position) {
        super(position);
    }

    public Ladybird() {
    }

    /**
     * Possible movement for the ladybird.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal
     * @return
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        boolean isOccupied = false;
        Position wall = nextWall(board, isOccupied, position, direction, animals);
        if (wall == null) {
            return null;
        }
        if (isInside(board, wall)) {
            if (board.getSquareType(wall)
                    == SquareType.GRASS && !isOccupied) {
                setPositionOnBoard(wall);
                return wall;
            } else if (board.getSquareType(wall) == SquareType.STAR) {
                super.setOnStar(true);
                board.GrassSets(wall);
                setPositionOnBoard(wall);
                return wall;
            } else if (board.getSquareType(wall) == null) {
                setPositionOnBoard(null);
                return null;
            }
            if (isOccupied) {
                return position;
            }
        }
        super.setPositionOnBoard(null);
        return null;
    }

    /**
     * Look if the naimal can move.
     *
     * @param board Board
     * @param isOccupied Boolean
     * @param position Position
     * @param direction Direction
     * @param animals Animal[]
     * @return position
     */
    private Position nextWall(Board board, boolean isOccupied,
            Position position, Direction direction, Animal[] animals) {
        Position nextposition = position.next(direction);
        Position thirdposition = position.next(direction).next(direction);
        int j = 0;
        while (j < animals.length && !isOccupied) {
            if (animals[j].getPositionOnBoard().equals(position.next(direction))) {
                isOccupied = true;
            }
            j++;
        }
        //Premier test avant de bouger
        if (isOccupied) {
            return position;
        }
        //si pas occupé directement  en face :
        if (board.isInside(nextposition)) {
            //TESTS POUR LES MURS
            return Wall(board, position, nextposition, thirdposition, direction);
            // POUR LES AUTRES ANIMAUX 
        } else if (isOccupied) {
            return isOccupied(isOccupied, position, nextposition, thirdposition, animals);
        }

        return null;
    }

    /**
     * Look if there is an animal in the next case.
     *
     * @param isOccupied boolean
     * @param position Position
     * @param nextposition Position
     * @param thirdposition Position
     * @param animals Animal[]
     * @return Position
     */
    private Position isOccupied(boolean isOccupied, Position position, Position nextposition, Position thirdposition, Animal[] animals) {
        if (!isOccupied) {
            //Reteste avec la nouvelle case en face , d'ou le thirdposition
            for (int i = 0; i < animals.length && !isOccupied; i++) {
                if (animals[i].getPositionOnBoard().equals(thirdposition)) {
                    isOccupied = true;
                }
            }
            if (!isOccupied) {
                return thirdposition;
            }
            return nextposition;
        } else {
            return position;
        }
    }

    /**
     * Look the different next wall.
     *
     * @param board Board
     * @param position Position
     * @param nextposition Position
     * @param thirdposition Position
     * @param direction Direction
     * @return Position
     */
    private Position Wall(Board board, Position position, Position nextposition, Position thirdposition, Direction direction) {
        if (isNextWall(board, position, direction) || isNextWallOpposite(board, nextposition, direction)) {
            return position;
        } else if (secondWall(board, nextposition, direction) || secondWallOpposite(board, thirdposition, direction)) {
            return nextposition;

        } else if (board.getSquares()[thirdposition.getRow()][thirdposition.getColumn()].hasWall(direction)) {
            return thirdposition;
        }
        return thirdposition;
    }
}
