/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54317.humbug.model;

/**
 *
 * @author le-ni
 */
public class Ant extends Animal {

    public Ant() {
    }

    public Ant(Position position) {
        super(position);
    }

    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        boolean isOccupied = false;
        Position nextPosition = position.next(direction);
        if (nextPosition == null) {
            return null;
        }
        if (isInside(board, nextPosition)) {
            if (board.getSquareType(nextPosition)
                    == SquareType.GRASS && !isOccupied) {
                setPositionOnBoard(nextPosition);
                return nextPosition;
            } else if (board.getSquareType(nextPosition) == SquareType.STAR) {
                super.setOnStar(true);
                board.GrassSets(nextPosition);
                setPositionOnBoard(nextPosition);
                return nextPosition;
            } else if (board.getSquareType(nextPosition) == null) {
                setPositionOnBoard(null);
                return null;
            }
            if (isOccupied) {
                return position;
            }
        } else if (!isInside(board, position.next(direction))) {
            System.out.println("entre");
            super.setPositionOnBoard(position);
            return getPositionOnBoard();
        } else if (!isInside(board, position.next(direction).next(direction))) {
            System.out.println("entre");
            super.setPositionOnBoard(position.next(direction));
            return position.next(direction);
        }
        System.out.println("entre");
        return position;

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
