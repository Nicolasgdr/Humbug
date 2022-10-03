package g54317.humbug.model;

/**
 * Direction is use to move the animal in board you can use the
 * North/South/West/East to move.
 *
 * @author Gardeur Nicolas 54317
 */
public enum Direction {
    // attributs 

    NORTH(-1, 0),
    WEST(0, -1),
    EAST(0, 1),
    SOUTH(1, 0);

    /**
     * Constructors of deltaRow and deltaColumn.
     *
     * @param deltaRow
     * @param deltaColumn
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }
    /**
     * Private and final attributs.
     */
    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Simple getter of DeltaRow.
     *
     * @return deltaRow
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Simple getter of DeltaColumn.
     *
     * @return deltaColumn
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

    /**
     * Method return the wall opposite at the direction choose.
     *
     * @return mur opposite of the direction
     */
    public Direction opposite() {
        Direction mur = null;
        switch (this) {
            case NORTH:
                mur = SOUTH;
                break;
            case SOUTH:
                mur = NORTH;
                break;
            case EAST:
                mur = WEST;
                break;
            case WEST:
                mur = EAST;
                break;
            default:
                //message derreur pas de sout
                System.out.println("Valeur non valide");
        }
        return mur;
    }
}

