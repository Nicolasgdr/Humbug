package g54317.humbug.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static g54317.humbug.model.SquareType.GRASS;
import static g54317.humbug.model.SquareType.STAR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pierre Bettens (pbt) <pbettens@he2b.be>
 */
public class SpiderTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), null},
            {null, new Squares(GRASS), new Squares(GRASS)},
            {null, null, new Squares(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove() {
        System.out.println("move and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_endline() {
        setUp();
        System.out.println("move end line and fall");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS)},
            {null, null, new Squares(STAR)}
        });
        Spider instance = (Spider) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_tootheranimal() {
        System.out.println("move to other animal");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS)},
            {null, null, new Squares(STAR)}
        });
        animals[1] = new Snail(new Position(0, 2));
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Spider instance = (Spider) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside() {
        System.out.println("move next case null and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void testMove_passOnStar() {
        setUp();
        System.out.println("move and pass on star without win");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(STAR), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(STAR), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    @Test
    public void Wall() {
        System.out.println("Wall");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(STAR), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0))};

        (board.getSquares()[0][0]).northWall = true;
        (board.getSquares()[0][0]).southWall = true;
        (board.getSquares()[0][0]).eastWall = true;
        (board.getSquares()[0][0]).westWall = true;

        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 0);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
                 result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
                 result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
                 result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
        
    }

    @Test
    public void stopNextWall() {
        System.out.println("Wall at the end of line");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(STAR), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),};

        (board.getSquares()[0][3]).eastWall = true;

        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void ifAnimalandWall() {
        System.out.println("ifAnimal");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(STAR), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(GRASS), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Spider(new Position(0, 3))

        };
        (board.getSquares()[0][3]).setEastWall(true);
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void aboveStar() {
        System.out.println("aboveStar");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(STAR), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),

        };
        (board.getSquares()[0][3]).eastWall = true;
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
