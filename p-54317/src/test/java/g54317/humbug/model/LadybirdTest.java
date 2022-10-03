package g54317.humbug.model;

import static g54317.humbug.model.SquareType.GRASS;
import static g54317.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Gardeur Nicolas
 */
public class LadybirdTest {

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
            new Ladybird(new Position(0, 0))
        };
    }

    @Test
    public void testMove() {
        System.out.println("move");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);

    }

    @Test
    public void testWall() {
        System.out.println("testWall");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),};

        (board.getSquares()[0][0]).eastWall = true;
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 0);
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
            new Ladybird(new Position(0, 0)),
            new Snail(new Position(0, 1))
        };
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 0);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void ifOut() {
        System.out.println("ifOut");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0))
        };
        Ladybird instance = (Ladybird) animals[0];
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
            new Ladybird(new Position(0, 0))
        };
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null;
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
            new Ladybird(new Position(0, 0))

        };
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
    
    
        @Test
    public void secondWallopposite() {
        System.out.println("second Wall opposite");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(STAR), null},
            {null, null, new Squares(GRASS), null}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0))
        };
        (board.getSquares()[0][2]).westWall = true;
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
    
     
}


