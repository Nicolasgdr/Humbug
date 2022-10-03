package g54317.humbug.model;

/**
 * Square on the board. A square has a type grass or star and it’s all. ∗A
 * Square doesn’t know where it is on the board. Wall blocked tthe way
 * South/North/West/East is the direction.
 *
 * @author Gardeur Nicolas 54317
 */
public class Squares{

    boolean northWall;
    boolean southWall;
    boolean westWall;
    boolean eastWall;

    private SquareType type;

    /**
     * Constructor whitout parameters.
     */
    public Squares() {
    }

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     *
     */
    public Squares(SquareType type) {
        this.type = type;
    }

    /**
     *
     * @param type SquareType.
     * @param northWall boolean wall at north.
     * @param southWall boolean wall at south.
     * @param westWall boolean wall at west.
     * @param eastWall boolean wall at east.
     */
    public Squares(SquareType type, boolean northWall, boolean southWall, boolean westWall, boolean eastWall) {
        this.type = type;
        this.northWall = northWall;
        this.southWall = southWall;
        this.westWall = westWall;
        this.eastWall = eastWall;

    }

    /**
     * Simple constructor of NorthWall.
     *
     * @return NorthWall in boolean.
     */
    public boolean isNorthWall() {
        return northWall;
    }

    /**
     * Simple constructor of SouthWall.
     *
     * @return SouthWall in boolean.
     */
    public boolean isSouthWall() {
        return southWall;
    }

    /**
     * Simple constructor of WestWall.
     *
     * @return WestWall in boolean
     */
    public boolean isWestWall() {
        return westWall;
    }

    /**
     * Simple constructor of EastWall.
     *
     * @return EastWall in boolean.
     */
    public boolean isEastWall() {
        return eastWall;
    }

    /**
     * Simple getter of type.∗
     *
     * @return type of Square
     *
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Simple setter of type.
     *
     * @param type of void.
     */
    public void setType(SquareType type) {
        this.type = type;
    }

    /**
     *
     * @param northWall boolean northWall = false.
     * @param southWall boolean SouthWall = false.
     * @param westWall boolean westWall = false.
     * @param eastWall boolean eastWall = false.
     */
    public Squares(boolean northWall, boolean southWall, boolean westWall, boolean eastWall) {
        this.northWall = false;
        this.southWall = false;
        this.westWall = false;
        this.eastWall = false;
    }

    /**
     * Simple setter of NorthWall.
     *
     * @param northWall boolean.
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * Simple setter of SouthWall.
     *
     * @param southWall boolean.
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * Simple setter of WestWall.
     *
     * @param westWall boolean.
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Simple setter of EastWall.
     *
     * @param eastWall boolean.
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    /**
     * Haswall return if there is a wall or not, its impossible to cross the
     * wall.
     *
     * @param direction Direction.
     * @return boolean if there is a wall or ot.
     */
    public boolean hasWall(Direction direction) {
        boolean verifMur = false;
        if (null != direction) {
            switch (direction) {
                case NORTH:
                    verifMur = northWall;
                    break;
                case SOUTH:
                    verifMur = southWall;
                    break;
                case EAST:
                    verifMur = eastWall;
                    break;
                case WEST:
                    verifMur = westWall;
                    break;
                default:
                    //erreur
                    break;
            }
        }
        return verifMur;
    }
}
