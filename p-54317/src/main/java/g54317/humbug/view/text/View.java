package g54317.humbug.view.text;

import g54317.humbug.model.Board;
import g54317.humbug.model.Direction;
import g54317.humbug.model.Position;
import g54317.humbug.model.SquareType;
import java.util.Scanner;
import g54317.humbug.model.Animal;
import g54317.humbug.model.Bumbelbee;
import g54317.humbug.model.Butterfly;
import g54317.humbug.model.Spider;
import g54317.humbug.model.Snail;
import g54317.humbug.model.Grasshopper;
import g54317.humbug.model.Ladybird;

/**
 * View of the game
 *
 * @author Gardeur Nicolas 54317
 */
public class View implements InterfaceView {

    /**
     * Show the board of the game, different column and different row, color
     * green for the floor, like grass.
     *
     * @param board Board
     * @param animals Animal[]
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void displayBoard(Board board, Animal... animals) {
        String s, t, n, tmp, w, y, z;
        for (int i = 0; i < board.getNbRow(); i++) {
            s = "";
            t = "";
            n = "";
            w = "";
            for (int j = 0; j < board.getNbColumn(); j++) {
                Position position = new Position(i, j);
                if (board.isInside(position)) {
                    tmp = "";

                    if (ifWall(board, position, Direction.NORTH)) {
                        t += "\u001b[42mwwwwww\u001b[0m";
                    } else {
                        t += "\u001b[42m------\u001b[0m";
                    };
                    if (ifWall(board, position, Direction.SOUTH)) {
                        w += "\u001b[42mwwwwww\u001b[0m";
                    } else {
                        w += "\u001b[42m------\u001b[0m";
                    };
                    if (ifWall(board, position, Direction.WEST)) {
                        y = "w";
                    } else {
                        y = "|";
                    }
                    if (ifWall(board, position, Direction.EAST)) {
                        z = "w";
                    } else {
                        z = "|";
                    }
                    s += "\u001b[42m" + y + "    " + z + "\u001b[0m";

                    if (board.getSquareType(position) == SquareType.GRASS) {
                        for (Animal animal : animals) {
                            if (animal.getPositionOnBoard().equals(position) && !animal.isOnStar()) {
                                if (animal.getClass().equals(Snail.class)) {
                                    tmp = "\u001b[42m" + y + " SN " + z + "\u001b[0m";
                                }
                                if (animal.getClass().equals(Spider.class)) {
                                    tmp = "\u001b[42m" + y + " SP " + z + "\u001b[0m";
                                }
                                if (animal.getClass().equals(Grasshopper.class)) {
                                    tmp = "\u001b[42m" + y + " GA " + z + "\u001b[0m";
                                }
                                if (animal.getClass().equals(Bumbelbee.class)) {
                                    tmp = "\u001b[42m" + y + " BE " + z + "\u001b[0m";
                                }

                                if (animal.getClass().equals(Ladybird.class)) {
                                    tmp = "\u001b[42m" + y + " LB " + z + "\u001b[0m";
                                }
                                if (animal.getClass().equals(Butterfly.class)) {
                                    tmp = "\u001b[42m" + y + " BF " + z + "\u001b[0m";
                                }

                            }
                        }
                        if (tmp == "") {
                            tmp = "\u001b[42m" + y + "    " + z + "\u001b[0m";
                        }
                        n += tmp;
                    }
                    if (board.getSquareType(position) == SquareType.STAR) {
                        n += "\u001b[42m" + y + " \u001b[31m**\u001b[0m\u001b[42m "
                                + z + "\u001b[0m";
                    }
                } else {
                    s += "      ";
                    t += "      ";
                    n += "      ";
                    w += "      ";
                }
            }
            System.out.println(t);
            System.out.println(s);
            System.out.println(n);
            System.out.println(s);
            System.out.println(w);
        }

    }

    /**
     * Check if there is a wall.
     *
     * @param board Board
     * @param position Position
     * @param direction Direction
     * @return boolean if wall or not
     */
    private boolean ifWall(Board board, Position position, Direction direction) {
        return (board.getSquares()[position.getRow()][position.getColumn()].hasWall(direction));
    }

    /**
     * Show the error message from other method.
     *
     * @param message String
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Ask next position at the player.
     *
     * @return position( the row , and the column)
     */
    @Override
    public Position askPosition() {
        System.out.println("Veuillez entrer une rangée");
        int row = valeurPositive();

        System.out.println("Veuillez entrer une colonne");
        int column = valeurPositive();

        return new Position(row, column);
    }

    /**
     * Recup row enter by the user with the scanner.
     *
     * @return the number enter
     */
    private int verifPositionNb() {
        Scanner keyboard = new Scanner(System.in);
        while (!keyboard.hasNext()) {
            keyboard.next();
            System.out.println("La valeur est pas un nombre");
        }
        return keyboard.nextInt();
    }

    private int valeurPositive() {
        int z = verifPositionNb();
        while (z < 0) {
            System.out.println("La valeur n'est pas possitive");
            z = verifPositionNb();
        }
        return z;
    }

    /**
     * Ask next direction at the players.
     *
     * @return direction
     */
    @Override
    public Direction askDirection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("veuillez entrer une direction N = Nord,"
                + " S = South, W = Weast, E = East");
        String s = null;
        while (s == null) {

            s = keyboard.nextLine();
            switch (s.toUpperCase()) {
                case "N":
                    return Direction.NORTH;
                case "S":
                    return Direction.SOUTH;
                case "E":
                    return Direction.EAST;
                case "W":
                    return Direction.WEST;
                default:
                    //
                    System.out.println("Saisie non valide");
                    s = null;
            }
        }
        return null;
    }

    /**
     * Show the number of remaining movements.
     *
     * @param RemainingMoves int
     */
    @Override
    public void displayRemainingMoves(int RemainingMoves) {
        System.out.println("Il reste " + RemainingMoves + " mouvements  possible");
    }

}
