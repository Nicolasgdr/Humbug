package g54317.humbug.model;

import static g54317.humbug.model.SquareType.GRASS;
import static g54317.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Gardeur Nicolas 54317
 */
public class GrasshopperTest {
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
            new Grasshopper(new Position(0, 0))
        };
    }

    @Test
    public void testMove() {
        System.out.println("move");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
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
            new Grasshopper(new Position(0, 0)),};

        (board.getSquares()[0][0]).eastWall = true;
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
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
            new Grasshopper(new Position(0, 0))
        };
        Grasshopper instance = (Grasshopper) animals[0];
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
            new Grasshopper(new Position(0, 0))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    @Test
    public void ifAnimal(){
        System.out.println("ifAnimal");
        board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Grasshopper(new Position(0, 1))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        System.out.println(animals[0].positionOnBoard.getRow()+ ""+ animals[0].positionOnBoard.getColumn());
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        System.out.println(animals[0].positionOnBoard.getRow()+ ""+ animals[0].positionOnBoard.getColumn());
        assertEquals(expResult, result); 
    }
}
