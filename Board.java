/**
 * Created by Andrew Peterson on 11/1/2015.
 */
public class Board {

    String[][] hitBoard = new String[10][10];
    String[][] board;
    String[] alph = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    int[] rows = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] directons = new String[]{"up", "down", "left", "right"};
    String[] compBoats = new String[]{"AIRCRAFT CARRIER", "BATTLESHIP", "SUBMARINE", "CRUISER", "PATROL BOAT"};
    Boat[] boats;
    int lv;
    private int column;
    private int row;
    private int computerOrPlayer = 0;
    private int numbBoats;
    //private ComputerPlayer computer;
    private Player1 player;
    private String[][][] boatPos = new String[5][10][10];
    public static Boat b = null;

    public Board(Player1 player) {
        computerOrPlayer = 0;
        this.player = player;
        row = 10;
        column = 10;
        board = new String[10][10];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = "  -  ";
                hitBoard[i][j] = "  -  ";
            }
        }
        lv = 0;
        boats = new Boat[5];
    }

    public Board(Player1 player, int id) {
        this.player = player;
        computerOrPlayer = 0;
        //this.computer = computer;
        row = 10;
        column = 10;
        board = new String[10][10];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = "  -  ";
                hitBoard[i][j] = "  -  ";
            }
        }
        lv = 0;
        boats = new Boat[5];
        int location = 0;
        while (true) {
            if (location == 0) {

                int firstCoor = (int) (Math.random() * 10);
                String secondCoor = alph[(int) (Math.random() * 10)];
                String direction = directons[(int) (Math.random() * 4)];
                if (!setBoat(5, firstCoor, secondCoor, direction, "AIRCRAFT CARRIER")) {
                    continue;
                } else {
                    location++;
                }
            }
            if (location == 1) {
                int firstCoor = (int) (Math.random() * 10);
                String secondCoor = alph[(int) (Math.random() * 10)];
                String direction = directons[(int) (Math.random() * 4)];
                if (!setBoat(4, firstCoor, secondCoor, direction, "BATTLESHIP")) {
                    continue;
                } else {
                    location++;
                }
            }
            if (location == 2) {
                int firstCoor = (int) (Math.random() * 10);
                String secondCoor = alph[(int) (Math.random() * 10)];
                String direction = directons[(int) (Math.random() * 4)];
                if (!setBoat(3, firstCoor, secondCoor, direction, "SUBMARINE")) {
                    continue;
                } else {
                    location++;
                }
            }
            if (location == 3) {
                int firstCoor = (int) (Math.random() * 10);
                String secondCoor = alph[(int) (Math.random() * 10)];
                String direction = directons[(int) (Math.random() * 4)];
                if (!setBoat(3, firstCoor, secondCoor, direction, "CRUISER")) {
                    continue;
                } else {
                    location++;
                }
            }
            if (location == 4) {
                int firstCoor = (int) (Math.random() * 10);
                String secondCoor = alph[(int) (Math.random() * 10)];
                String direction = directons[(int) (Math.random() * 4)];
                if (!setBoat(2, firstCoor, secondCoor, direction, "PATROL BOAT")) {
                    continue;
                } else {
                    location = 0;
                }
            }
            break;
        }

    }

    public void getHitBoard() {
        System.out.print("   ");
        for (int i = 0; i < alph.length; i++) {
            System.out.print(alph[i] + "    ");
        }
        for (int i = 0; i < hitBoard[0].length; i++) {
            System.out.println();
            if (i != 9)
                System.out.print(i + 1 + " ");
            else
                System.out.print(i + 1);
            for (int j = 0; j < hitBoard.length; j++) {
                System.out.print(hitBoard[i][j]);
            }

            System.out.println();
        }
    }

    public void setPlayName(String name) {
        player.setName(name);
    }

    public void getBoard() {
        System.out.print("    ");
        for (int i = 0; i < alph.length; i++) {
            System.out.print(alph[i] + "    ");
        }
        for (int i = 0; i < board[0].length; i++) {
            System.out.println();
            if (i != 9)
                System.out.print(i + 1 + " ");
            else
                System.out.print(i + 1);
            for (int j = 0; j < board.length; j++) {

                System.out.print(board[i][j]);
            }

            System.out.println();
        }
    }

    private boolean canSetBoat(String name, int size, int row, int column, String direction) {
        boats[lv] = new Boat(name, size, row, column, direction);
        // if(computerOrPlayer == 0) {
        if (boats[lv].checkPosition(row, column, size, direction, player) && !inBoatSpace(boats[lv])) {
            numbBoats++;
            return true;
        } else {
            boats[lv] = null;
            return false;
        }

    }

    private boolean inBoatSpace(Boat b) {
        int[][] b1Space = b.boatSpace(b.sizeOfBoat());
        int i = 0;
        while (i < numbBoats) {
            if (boats[i].getBoatName().equals(b.getBoatName()))
                continue;
            int[][] b2Space = boats[i].boatSpace(boats[i].sizeOfBoat());
            for (int j = 0; j < b1Space[0].length; j++) {
                for (int k = 0; k < b1Space.length; k++) {
                    if (b1Space[j][k] == 1) {
                        if (b2Space[j][k] == 1)
                            return true;
                        if (j != 9 && b2Space[j + 1][k] == 1)
                            return true;
                        if (j != 0 && b2Space[j - 1][k] == 1)
                            return true;
                        if (k != 9 && b2Space[j][k + 1] == 1)
                            return true;
                        if (k != 0 && b2Space[j][k - 1] == 1)
                            return true;
                        if (j != 9 && k != 9 && b2Space[j + 1][k + 1] == 1)
                            return true;
                        if (j != 9 && k != 0 && b2Space[j + 1][k - 1] == 1)
                            return true;
                        if (j != 0 && k != 0 && b2Space[j - 1][k - 1] == 1)
                            return true;
                        if (j != 0 && k != 9 && b2Space[j - 1][k + 1] == 1)
                            return true;
                    }
                }
            }
            i++;
        }
        return false;


    }

    public boolean setBoat(int size, int row, String col, String direction, String name) {
        String[][] newBoard = new String[10][10];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[i][j] = "  -  ";
            }
        }
        int column = 0;
        for (int i = 0; i < alph.length; i++) {
            if (alph[i].equals(col)) {
                column = i;
                break;
            } else if (i + 1 == alph.length)
                return false;
        }
        if (!canSetBoat(name, size, row, column, direction))
            return false;
        player.addBoat(boats[lv]);
        if (direction.equalsIgnoreCase("right")) {
            int k = 0;
            while (k < size) {
                board[row][column] = "  o  ";
                newBoard[row][column] = "  o  ";
                column++;
                k++;
            }
            boatPos[lv] = newBoard;
            lv++;
            return true;
        } else if (direction.equalsIgnoreCase("left")) {
            int k = 0;
            while (k < size) {
                board[row][column] = "  o  ";
                newBoard[row][column] = "  o  ";
                column--;
                k++;
            }
            boatPos[lv] = newBoard;
            lv++;
            return true;

        } else if (direction.equalsIgnoreCase("up")) {
            int k = 0;
            while (k < size) {
                board[row][column] = "  o  ";
                newBoard[row][column] = "  o  ";
                row--;
                k++;
            }
            boatPos[lv] = newBoard;
            lv++;
            return true;

        } else if (direction.equalsIgnoreCase("down")) {
            int k = 0;

            while (k < size) {
                board[row][column] = "  o  ";
                newBoard[row][column] = "  o  ";
                row++;
                k++;
            }
            boatPos[lv] = newBoard;
            lv++;
            return true;
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    private Boat getBoatAt(int i) {
        return boats[i];
    }

    public int getColumn() {
        return column;
    }

    public void announcement(String name, int var, Player1 play) {
        String attacker = play.getName();
        String punct = "'s";
        if (play.getName().equals("computer"))
            attacker = "Computer";
        if (play.getName().endsWith("s")) {
            punct = "'";
        }
        if (var == 1)
            System.out.printf("\nYou sunk %s " + name + "!\n\n", attacker + punct);
        else if (var == 0)
            System.out.println("Hit!\n");
        else
            System.out.println("Miss!");
    }

    public String sunkBoatName(String name){
//        for (String i : compBoats) {
//            if(name.equalsIgnoreCase(i))
//                return i;
//
//        }
        return name;
    }

    public int strike(int row, String col) {
        int column = 0;
        for (int i = 0; i < alph.length; i++) {
            if (alph[i].equals(col))
                column = i;
        }

        if (row < 10 && board[row][column].equals("  o  ")) {
            if (hitBoard[row][column].equals("  H  ") || hitBoard[row][column].equals("  M  "))
                return 2;
            hitBoard[row][column] = "  H  ";
            board[row][column] = "  x  ";
            for (int i = 0; i < 5; i++) {

                if (boatPos[i][row][column].equals("  o  ")) {
                    if (getBoatAt(i).updateNumbHits() == 1) {
                        announcement(getBoatAt(i).getBoatName(), 1, player);
                        player.updateNumbBoats();
                        sunkBoatName(getBoatAt(i).getBoatName());
                        b = getBoatAt(i);
                        return 1;
                    }
                    break;
                }


            }
            announcement("no ship", 0, player);
            System.out.println("Strike: Hit");
            return 0;
        } else if (row < 10 && board[row][column].equals("  -  ")) ;
        {
            hitBoard[row][column] = "  M  ";
            announcement("no ship", -1, player);
            System.out.println("Strike: Miss");
            return -1;
        }


    }


}
