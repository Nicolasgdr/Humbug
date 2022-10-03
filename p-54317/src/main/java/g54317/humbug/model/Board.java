package g54317.humbug.model;

import static g54317.humbug.model.SquareType.GRASS;

/**
 *
 * @author Gardeur Nicolas 54317
 */
public class Board {

    /**
     * Private attributs Create array squares 2dimension.
     */
    private Squares[][] squares;

    /**
     * Constructor of board.
     *
     * @param square
     */
    Board(Squares[][] squares) {
        this.squares = squares;

    }

    /**
     * Constructor whitout parameter
     */
    public Board() {

    }

    /**
     * Simple setter of squares.
     *
     * @param square void
     */
    public void setSquares(Squares[][] square) {
        this.squares = square;
    }

    /**
     * Simple getter of Square.
     *
     * @return squares
     */
    public Squares[][] getSquares() {
        return squares;
    }

    /**
     * Look if the player choose a good coordinates in the board or not.
     *
     * @param position Position
     * @return boolean if grass or star of the board
     */
    public boolean isInside(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("En dehors du tableau");
        }

        if (position.getColumn() < 0 || position.getRow() < 0
                || position.getRow() >= getNbRow() || position.getColumn()
                >= getNbColumn()) {
            return false;
        }
        if (squares[position.getRow()][position.getColumn()] == null) {
            return false;
        }
        {
            return position.getColumn() < this.getNbColumn()
                    && position.getRow() < this.getNbRow();

        }

    }

    /**
     * Show the type of the squares : GRASS or STAR.
     *
     * @param position Position
     * @return type of squares
     */
    public SquareType getSquareType(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("impossible d'avoir une "
                    + "valeur null");

        }
        if (squares[position.getRow()][position.getColumn()] == null) {
            throw new IllegalArgumentException("le tableau ne peut "
                    + "pas être null");
        }

        return squares[position.getRow()][position.getColumn()].getType();

    }

    /**
     * Simple getter of NbRow.
     *
     * @return numbers of row
     */
    public int getNbRow() {
        return squares.length;
    }

    /**
     * Simple getter of NbColumn.
     *
     * @return numbers of Column
     */
    public int getNbColumn() {
        return squares[0].length;
    }

    /**
     * Change position on grass.
     *
     * @param position Position
     */
    public void GrassSets(Position position) {
        this.squares[position.getNbRow()][position.getNbColumn()].setType(GRASS);
    }

}
