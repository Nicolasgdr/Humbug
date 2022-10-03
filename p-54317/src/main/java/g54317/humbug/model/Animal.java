package g54317.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Super class where animals are united.
 *
 * @author Gardeur Nicolas 54317
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Grasshopper.class),
    @Type(value = Butterfly.class),
    @Type(value = Ladybird.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),})
public abstract class Animal {

    /**
     * Constructor whitout parameter.
     */
    public Animal() {
    }

    protected Position positionOnBoard;
    private boolean onStar;

    /**
     * Simple getter of PositionOnBoard.
     *
     * @return positionOnBoard
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Constructor of Animal.
     *
     * @param position¨Position
     */
    public Animal(Position position) {
        this.onStar = false;
        this.positionOnBoard = position;

    }

    /**
     * Constructor if isOnstar.
     *
     * @return onStar
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Setter of PositionOnBoard.
     *
     * @param positionOnBoard Position
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Simple setter OnStar.
     *
     * @param onStar boolean
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Wall on opposite direction in next position.
     *
     * @param board Board
     * @param position Position
     * @param direction Direction
     * @return wall or not
     */
    /**
     * Abstract methode move to define.
     *
     * @param board Board
     * @param direction Direction
     * @param animals Animal...
     * @return boolean b
     */
    public abstract Position move(Board board,
            Direction direction, Animal... animals);

    /**
     * Look of the next position is free or not.
     *
     * @param position Position
     * @param animals Animal...
     * @return boolean b
     */
    protected boolean isFree(Position position, Animal... animals) {
        boolean b = true;
        int i = 0;
        while (i < animals.length && b) {
            if (animals[i].getPositionOnBoard().equals(position) && !animals[i].isOnStar()) {
                b = false;
            }
            i++;
        }
        return b;
    }

    /**
     * Look if the animal is arrived on the star.
     *
     * @param board Board
     * @param animals Animal...
     * @return onStar
     */
    public boolean arrived(Board board, Animal[] animals) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].isOnStar()) {
                board.GrassSets(positionOnBoard);
                onStar = true;
            }
        }

        return onStar = false;
    }

    /**
     * Check if the ladybird is in the board in the next direction.
     *
     * @param board Board
     * @param position Position
     * @return boolean inside or not
     */
    protected boolean isInside(Board board, Position position) {
        boolean a = board.isInside(position);
        return a;
    }

    /**
     * Check all possible movements that an flying animal can make
     *
     * @param board Board
     * @param position
     * @param direction Direction
     * @param animals Animal[]
     * @return Destination
     */
    protected Position ifFly(Board board, Position position, Direction direction, Animal... animals) {
        Position destination = position;
        boolean isOccupied = true;
        boolean isDestinationFree = false;

        while (!isDestinationFree) {
            isOccupied = false;
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(destination)) {
                    isOccupied = true;
                }
            }
            if (isOccupied) {
                destination = destination.next(direction);
            } else {
                isDestinationFree = true;
            }
        }

        if (isInside(board, destination)) {
            if (board.getSquareType(destination) == SquareType.STAR) {
                setOnStar(true);
                board.GrassSets(destination);
            }
            setPositionOnBoard(destination);
            return destination;
        }
        setPositionOnBoard(null);
        return null;
    }
    protected boolean isNextWall(Board board, Position position, Direction direction) {
        return board.getSquares()[position.getRow()][position.getColumn()].hasWall(direction);
    }

    protected boolean isNextWallOpposite(Board board, Position position, Direction direction) {
        return board.getSquares()[position.getRow()][position.getColumn()].hasWall(direction.opposite());
    }

    protected boolean secondWall(Board board, Position nextposition, Direction direction) {
        return board.getSquares()[nextposition.getRow()][nextposition.getColumn()].hasWall(direction);
    }

    protected boolean secondWallOpposite(Board board, Position nextposition, Direction direction) {
        return board.getSquares()[nextposition.getRow()][nextposition.getColumn()].hasWall(direction.opposite());
    }

}
