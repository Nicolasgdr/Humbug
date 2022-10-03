package g54317.humbug.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static g54317.humbug.model.SquareType.GRASS;
import static g54317.humbug.model.SquareType.STAR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gardeur Nicolas 54317
 */
public class BumbelbeeTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(1, 2))
        };
    }

    @Test
    public void testMove() {
        System.out.println("move");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);

    }
    @Test 
    public void testWall(){
        System.out.println("testWall");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),};

        (board.getSquares()[0][0]).eastWall = true;
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    @Test
    public void ifAnimal() {
        System.out.println("ifAnimal");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Snail(new Position(0, 1))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    @Test
    public void ifOut(){
        System.out.println("ifOut");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
@Test
    public void nextCaseFree() {
        System.out.println("nextCaseFree");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), null, new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0))
        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    @Test
    public void aboveStar() {
        System.out.println("aboveStar");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(STAR), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(GRASS), null}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),

        };
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}


