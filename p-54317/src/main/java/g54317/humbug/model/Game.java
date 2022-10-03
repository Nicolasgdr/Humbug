package g54317.humbug.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Launch the game Load animal and game board.
 *
 * @author Gardeur Nicolas 54317
 */
public class Game implements Model {

    /**
     * Private Attributs.
     */
    private Board board;
    private Animal[] animals;
    int remainingMoves;
    int currentLevel;

    /**
     * Board of the game. Constructor of the board
     *
     * @return board
     */
    public Board board() {
        return board;
    }

    /**
     * Board of animal. Constructor of Animal
     *
     * @return animals
     */
    public Animal[] animals() {

        return animals;
    }

    /**
     * Simple getter of Board.
     *
     * @return board
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Simple getter of Animals.
     *
     * @return animals
     */
    @Override
    public Animal[] getAnimals() {
        Animal[] present = animalBoard(List.of(animals));
        if (present.length != 0) {
            this.animals = present;
        }
        return animals;
    }

    /**
     * Start the level and initial the board and animals.
     *
     * @param level int
     */
    @Override
    public void startLevel(int level) {
        board = Level.getLevel(level).getBoard();

        animals = Level.getLevel(level).getAnimals();

        remainingMoves = Level.getLevel(level).getnMoves();
    }

    /**
     * Check if the level is over or not.
     *
     * @return boolean level
     */
    public boolean levelIsOver() {
        int animalIndex = 0;
        //On suppose que tous les animaux sont sur une étoile 
        boolean allOnStar = true;
        while (animalIndex < animals.length && allOnStar) {
            allOnStar = animals[animalIndex].isOnStar();
            animalIndex++;
        }
        return allOnStar;

    }

    /**
     * If animal can or can't move.
     *
     * @param verif Position
     * @param direction Direction
     */
    @Override
    public void move(Position verif, Direction direction) {
        if (LevelStatus.IN_PROGRESS != getLevelStatus()) {
            throw new IllegalStateException("STATE");//
        }
        boolean ifmove = true;
        int i = 0;
        while (i < getAnimals().length && ifmove) {
            if (verif.equals(getAnimals()[i].getPositionOnBoard())) {

                verif = getAnimals()[i].move(board, direction, getAnimals());
                remainingMoves--;
                ifmove = false;

            }

            i++;
        }
    }

    /**
     * Simple getter of RemainingMoves.
     *
     * @return remainingMoves
     */
    @Override
    public int getRemainingMoves() {
        return remainingMoves;
    }

    /**
     * Check is the game are finish, in progress, not started or win.
     *
     * @return levelstatus
     */
    @Override
    public LevelStatus getLevelStatus() {
        if (!levelIsOver() && getRemainingMoves() == 0) {
            return LevelStatus.FAIL;
        }
        if (!allOnBoard(board, animals)) {
            return LevelStatus.FAIL;
        }
        if (!levelIsOver()) {
            return LevelStatus.IN_PROGRESS;
        }
        if (levelIsOver() && getRemainingMoves() == 0) {
            return LevelStatus.WIN;
        }

        for (Animal animal : animals) {
            System.out.println(animal.getPositionOnBoard());
        }
        return LevelStatus.NOT_STARTED;
    }

    /**
     * If all animal is on board at the start of the game.
     *
     * @param board Board
     * @param animals Animal[]
     * @return conditionStop
     */
    private boolean allOnBoard(Board board, Animal... animals) {
        int animalIndex = 0;
        boolean conditionStop = false;
        while (animalIndex < animals.length && !conditionStop) {
            conditionStop = board.isInside(animals[animalIndex].getPositionOnBoard());
            animalIndex++;
        }
        return conditionStop;
    }

    /**
     *
     * @param animals Animal{}
     * @return turnListToArrays
     */
    private Animal[] animalBoard(List<Animal> animals) {
        List<Animal> animalPresent = new ArrayList<>();
        for (Animal animal : animals) {
            if (!animal.isOnStar()) {
                animalPresent.add(animal);
            }
        }
        return turnListToArrays(animalPresent);
    }

    /**
     *
     * @param animalPresent Animal{}
     * @return animalsPresent
     */
    private Animal[] turnListToArrays(List<Animal> animalPresent) {
        Animal[] animalsPresent = new Animal[animalPresent.size()];
        for (int i = 0; i < animalsPresent.length; i++) {
            animalsPresent[i] = animalPresent.get(i);
        }
        return animalsPresent;
    }
}
