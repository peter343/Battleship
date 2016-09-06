/**
 * Boat.java
 * <p>
 * Boat.java creates Boat objects. There are 5 different boats in BattleShip: Aircraft Carrier, Battleship,
 * Submarine, Cruiser, and Patrol Boat. Each except for the submarine and cruiser have different sizes.
 * Boat.java keeps track of each boats' position and the space around each one. Boats must not be placed over one
 * another, must not be placed next to each other, and must not extend over the border of the 'ocean". While Boat
 * .java does check the boats position and keep track of the space around that certain instance, Board.java is the
 * class that keeps track of each boats position and space in relation to other boats.
 *
 * @author Andrew Peterson
 * @version 11.2.15
 */
public class Boat {

    private String name;
    private int size;
    private int[] position = new int[2];
    private int numbHitsRemaining;
    private int row;
    private int col;
    private String direction;

    public Boat(String name, int size, int row, int column, String direction) {
        this.direction = direction;
        this.name = name;
        this.size = size;
        numbHitsRemaining = size;
        this.row = row;
        this.col = column;
        position[0] = row;
        position[1] = column;
    }

    public boolean checkPosition(int row, int col, int size, String direction, Player1 player) {


        Board b = new Board(player);
        int rLim = b.getColumn();
        int bLim = b.getRow();
        if (col < rLim && row < bLim && col >= 0 && row >= 0) {
            if (direction.equalsIgnoreCase("right")) {
                int farRight = col + (size - 1);
                return (farRight < rLim);

            } else if (direction.equalsIgnoreCase("left")) {
                int farLeft = col - (size - 1);
                return (farLeft >= 0);

            } else if (direction.equalsIgnoreCase("up")) {
                int farUp = row - (size - 1);
                return (farUp >= 0);

            } else if (direction.equalsIgnoreCase("down")) {
                int farDown = row + (size - 1);
                return (farDown < bLim);

            }
        }
        return false;


    }

    public int[][] boatSpace(int size) {
        int[][] ocean = new int[10][10];
        for (int i = 0; i < ocean[0].length; i++) {
            for (int j = 0; j < ocean.length; j++) {
                if (direction.equalsIgnoreCase("right")) {
                    int k = 0;
                    if (j == col && i == row) {
                        while (k < size) {
                            ocean[i][j] = 1;
                            j++;
                            k++;
                        }
                        return ocean;
                    }
                } else if (direction.equalsIgnoreCase("left")) {
                    int k = 0;
                    if (j == col && i == row) {
                        while (k < size) {
                            ocean[i][j] = 1;
                            j--;
                            k++;
                        }
                        return ocean;
                    }
                } else if (direction.equalsIgnoreCase("up")) {
                    int k = 0;
                    if (j == col && i == row) {
                        while (k < size) {
                            ocean[i][j] = 1;
                            i--;
                            k++;
                        }
                        return ocean;
                    }
                } else if (direction.equalsIgnoreCase("down")) {
                    int k = 0;
                    if (j == col && i == row) {
                        while (k < size) {
                            ocean[i][j] = 1;
                            i++;
                            k++;
                        }
                        return ocean;
                    }
                }

                ocean[i][j] = 0;
            }
        }

        return ocean;
    }

    public String getBoatName() {
        return name;
    }

    public int[] getPosition() {
        return position;
    }

    public int sizeOfBoat() {
        return size;
    }


    public int hitsLeft() {
        return numbHitsRemaining;
    }

    public int updateNumbHits() {
        numbHitsRemaining--;
        if (numbHitsRemaining == 0) {
            return 1;           //Boat is destroyed
        } else
            return 0;

    }

}
