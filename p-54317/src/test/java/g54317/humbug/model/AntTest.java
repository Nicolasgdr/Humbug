/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54317.humbug.model;

import static g54317.humbug.model.SquareType.GRASS;
import static g54317.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author le-ni
 */
public class AntTest {
    
    public AntTest() {
    }

  
        @Test
    public void nextCaseNull() {
        System.out.println("nextCaseFree");
        Board board = new Board(new Squares[][]{
            {new Squares(GRASS), null, new Squares(GRASS), new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        Animal []animals = new Animal[]{
            new Ant(new Position(0, 0))
        };
        Ant instance = (Ant) animals[0];
        Position expResult = new Position(0,0);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
       @Test
    public void nextCaseNull2() {
        System.out.println("nextCaseFree");
        Board board = new Board(new Squares[][]{
            {new Squares(GRASS), new Squares(GRASS), null, new Squares(GRASS)},
            {null, new Squares(GRASS), new Squares(GRASS), null},
            {null, null, new Squares(STAR), null}
        });
        Animal []animals = new Animal[]{
            new Ant(new Position(0, 0))
        };
        Ant instance = (Ant) animals[0];
        Position expResult = new Position(0,1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    } 
    
}
