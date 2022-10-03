package g54317.humbug.model;

/**
 * Position check the placement of the animals in the board You can choose
 * different animals with the position.
 *
 * @author Gardeur Nicolas 54317
 */
public class Position {

    /**
     * Private and final Attributs.
     */
    private final int row;
    private final int column;

    /**
     * Constructor column and row.
     *
     * @param row int
     * @param column int
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Constructor whitout parameters.
     */
    public Position() {
        this.row = 0;
        this.column = 0;
    }

    /**
     * Simple getter of row row from the board row is a int.
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Simple getter of column column from the board column is a int.
     *
     * @return column
     */
    public int getColumn() {
        return column;
    }

    /**
     * This method is supported for the benefit of hash tables such as those
     * provided by HashMap.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.row;
        hash = 29 * hash + this.column;
        return hash;
    }

    /**
     * Compared object and look if there are equals or not.
     *
     * @param obj boolean
     * @return boolean : equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    /**
     * Position of the player with the next direction.
     *
     * @param direction Direction
     * @return a new position
     */
    public Position next(Direction direction) {
        return new Position(direction.getDeltaRow() + row,
                direction.getDeltaColumn() + column);
    }

    /**
     * Simple getter of NbRow.
     *
     * @return row in int
     */
    public int getNbRow() {
        return row;
    }

    /**
     * Simple getter of NbColumn.
     *
     * @return column in int
     */
    public int getNbColumn() {
        return column;
    }

}
