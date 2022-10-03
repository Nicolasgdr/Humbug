package g54317.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Make different level in the game, for more difficulties.
 *
 * @author Gardeur Nicolas 54317
 */
public class Level {

    /**
     * Private attributs
     */
    private Board board;
    private Animal[] animals;
    private int nMoves;

    private static Level readLevel(int n) throws IOException {
        
            var objectMapper = new ObjectMapper();
            var inputStream = Level.class.getResourceAsStream(
                    "/data/level-" + n + ".json");
            var level = objectMapper.readValue(inputStream, Level.class);
            return level;
    
    }

    /**
     * constructor of board, animals, and nMoves
     *
     */
    public Level() {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Get the number of level that the players want.
     *
     * @param level int
     * @return nLevel
     */
    public static Level getLevel(int level) {
        try {
            Level nLevel = readLevel(level);
            return nLevel;
        } catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }
/**
 * Simple getter of getBoard.
 * @return board
 */
    public Board getBoard() {
        return board;
    }
/**
 * Simple getter of getAnimals.
 * @return 
 */
    public Animal[] getAnimals() {
        return animals;
    }
/**
 * Simple getter of getnMoves.
 * @return 
 */
    public int getnMoves() {
        return nMoves;
    }

}
