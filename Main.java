import java.util.Scanner;

/**
 * Created by Andrew Peterson on 11/1/2015.
 */
public class Main {
    private static final int MISS = -1;
    private static final int HIT = 0;
    private static Board player1;
    private static Board player2;
    private static Player1 p1;
    private static Player1 p2;
    private static String p1Name;
    private static String p2Name;
    private static boolean robot;
    //TODO Make the computer quit after a while
    int firstCoor = 0;
    String secondCoor = "";
    String direction = "";
    boolean even = true;

    String[] alph = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public static void main(String[] args) {
        Main m = new Main();
        int location = 0;
        int i = 0;
        robot = false;

        System.out.println("Enter Player 1's Name: ");
        Scanner s = new Scanner(System.in);
        p1Name = s.nextLine().trim();
        p1 = new Player1(p1Name);
        player1 = new Board(p1);

        System.out.println("Enter Player 2's Name or type 'computer' to play against a computer: ");
        p2Name = s.nextLine().trim();
        p2 = new Player1(p2Name);

        if (p2Name.equalsIgnoreCase("computer")) {
            player2 = new Board(p2, 1);
            robot = true;
        } else {
            player2 = new Board(p2);
        }


        while (true) {
            System.out.println();
            System.out.printf("\nPlayer %d, you have %d boats to place. Everyone else, look away now!%n", i + 1, 5 - p1
                    .getNumbBoats());
            player1.getBoard();
            if (location == 0) {
                System.out.println("Enter a coordinate and direction for your Aircraft Carrier (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player1.setBoat(5, m.firstCoor, m.secondCoor, m.direction, "AIRCRAFT " +
                        "CARRIER")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player1.getBoard();
                    location++;
                }
            }
            if (location == 1) {
                System.out.println("Enter a coordinate and direction for your Battleship (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player1.setBoat(4, m.firstCoor, m.secondCoor, m.direction, "BATTLESHIP")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player1.getBoard();
                    location++;
                }
            }
            if (location == 2) {
                System.out.println("Enter a coordinate and direction for your Submarine (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player1.setBoat(3, m.firstCoor, m.secondCoor, m.direction, "SUBMARINE")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player1.getBoard();
                    location++;
                }
            }
            if (location == 3) {
                System.out.println("Enter a coordinate and direction for your Cruiser (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player1.setBoat(3, m.firstCoor, m.secondCoor, m.direction, "CRUISER")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player1.getBoard();
                    location++;
                }
            }
            if (location == 4) {
                System.out.println("Enter a coordinate and direction for your Patrol Boat (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player1.setBoat(2, m.firstCoor, m.secondCoor, m.direction, "PATROL_BOAT")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    location = 0;
                }
            }
            i++;
            for (int j = 0; j < 101; j++) {
                System.out.println();
            }
            break;
        }
        while (true) {
            if (p2Name.equalsIgnoreCase("computer")) {
                break;
            }
            System.out.println();
            System.out.printf("\nPlayer %d, you have %d boats to place. Everyone else, look away now!%n", i + 1, 5 - p2
                    .getNumbBoats());
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
            }
            player2.getBoard();
            if (location == 0) {
                System.out.println("Enter a coordinate and direction for your Aircraft Carrier (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player2.setBoat(5, m.firstCoor, m.secondCoor, m.direction, "AIRCRAFT " +
                        "CARRIER")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player2.getBoard();
                    location++;
                }

            }
            if (location == 1) {
                System.out.println("Enter a coordinate and direction for your Battleship (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player2.setBoat(4, m.firstCoor, m.secondCoor, m.direction, "BATTLESHIP")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player2.getBoard();
                    location++;
                }
            }
            if (location == 2) {
                System.out.println("Enter a coordinate and direction for your Submarine (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player2.setBoat(3, m.firstCoor, m.secondCoor, m.direction, "SUBMARINE")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player2.getBoard();
                    location++;
                }
            }
            if (location == 3) {
                System.out.println("Enter a coordinate and direction for your Cruiser (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player2.setBoat(3, m.firstCoor, m.secondCoor, m.direction, "CRUISER")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else {
                    player2.getBoard();
                    location++;
                }
            }
            if (location == 4) {
                System.out.println("Enter a coordinate and direction for your Patrol Boat (i.e. 1A down): ");
                String input = s.nextLine();
                if (!m.validCoor(input) || !player2.setBoat(2, m.firstCoor, m.secondCoor, m.direction, "PATROL_BOAT")) {
                    System.out.println("Invalid input! Remember to follow the Coordinate format and placement rules!");
                    continue;
                } else location = 0;
            }
            for (int j = 0; j < 101; j++) {
                System.out.println();
            }
            break;
        }


        System.out.println("\n");
        System.out.println("Let the battle begin!\n");

        m.play();


    }

    private boolean validCoor(String coor) {

        try {
            String one = coor.substring(0, 1);
            String two = coor.substring(1, 2).toUpperCase();
            firstCoor = Integer.parseInt(one) - 1;
            secondCoor = two;
            direction = coor.substring(3).toLowerCase();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void devMenu(int whichPlayer) {
        Scanner d = new Scanner(System.in);
        System.out.println("          Dev Menu:          ");
        System.out.println("1...Get Enemy Board          ");
        System.out.println("2...Get Enemy Hit Board");
        System.out.println("3...Get Own Board");
        System.out.println("4...Get Own Hit Board");
        System.out.println("5...Sub in human player two");
        System.out.println("6...Sub in computer player two");
        System.out.println("7...Exit Dev Menu");
        System.out.println("\nEnter the number of the desired command: ");
        int choice = d.nextInt();

        if (choice == 1) {
            if (whichPlayer == 1) {
                player2.getBoard();
            } else {
                player1.getBoard();
            }
        } else if (choice == 2) {
            if (whichPlayer == 1) {
                player1.getHitBoard();
            } else {
                player2.getHitBoard();
            }
        } else if (choice == 3) {
            if (whichPlayer == 1) {
                player1.getBoard();
            } else {
                player2.getBoard();
            }
        } else if (choice == 4) {
            if (whichPlayer == 1) {
                player2.getHitBoard();
            } else {
                player1.getHitBoard();
            }
        } else if (choice == 5) {
            robot = false;
            System.out.println("Enter the player's name");
            d.nextLine();
            player2.setPlayName(d.nextLine());
        } else if (choice == 6) {
            robot = true;
            player2.setPlayName("computer");
        } else if (choice == 7) {
            return;
        }


    }

    public void play() {
        int player = 1;
        Scanner s = new Scanner(System.in);
        boolean left = false;
        boolean hasHit = false;
        boolean right = false;

        int surround = 0;
        int computerFC = 0;
        int computerSC = 0;
        int i = 0;
        int count = 1;
        int misses = 0;
        boolean verticalHits = false;
        boolean checkNext = false;
        boolean startOver = true;
        boolean horizontalHits = false;
        while (true) {

            if (player == 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println("Player 1, would you like to see your own board before you continue? (y or n): ");
                String str = s.nextLine();
                if (str.equals("y")) {
                    System.out.println("           MAKE SURE NO ONE IS LOOKING!\n");
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                    }
                    for (int j = 0; j < 101; j++) {
                        System.out.println();
                    }
                    System.out.println("               Player 1 Boat-Board:\n");
                    player1.getBoard();
                    System.out.println("\nEnter any key to continue...");
                    while (!s.hasNext()) {
                        continue;
                    }
                    for (int j = 0; j < 101; j++) {
                        System.out.println();
                    }
                    s.nextLine();
                }

                System.out.println("               Player 1 Hit-Board:\n");
                player2.getHitBoard();
                System.out.println("\nPlayer 1, where would you like to strike?: ");
                String strike = s.nextLine();
                int first;
                String second;
                try {
                    if (strike.equals("~")) {
                        devMenu(1);
                        continue;
                    }
                    first = Integer.parseInt(strike.substring(0, 1));
                    second = strike.substring(1).toUpperCase();
                    int hit = player2.strike(first - 1, second);
                    if (hit == 2) {
                        System.out.println("You have already fired at this spot, choose again!\n");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        continue;
                    }
                    System.out.println();
                    if (p2.getNumbBoats() == 0) {
                        System.out.printf("\n%s wins!", p1Name);
                        break;
                    }
                    if (!robot) {
                        player = 2;
                    } else {
                        player = 3;
                    }
                } catch (Exception e) {
                    System.out.println("\nInvalid strike, try again!");
                    continue;
                }

            }
            if (player == 2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println("Player 2, would you like to see your own board before you continue? (y or n): ");
                String str = s.nextLine();
                if (str.equals("y")) {
                    System.out.println("           MAKE SURE NO ONE IS LOOKING!\n");
                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                    }
                    for (int j = 0; j < 101; j++) {
                        System.out.println();
                    }
                    System.out.println("               Player 2 Boat-Board:\n");
                    player2.getBoard();
                    System.out.println("\nEnter any key to continue...");
                    while (!s.hasNext()) {
                        continue;
                    }
                    for (int j = 0; j < 101; j++) {
                        System.out.println();
                    }
                    s.nextLine();
                }

                System.out.println("               Player 2 Hit-Board:\n");
                player1.getHitBoard();
                System.out.println("\nPlayer 2, where would you like to strike?: ");
                String strike2 = s.nextLine();
                int first2;
                String second2;
                try {
                    if (strike2.equals("~")) {
                        devMenu(2);//TODO IMPLEMENT DEV MENU FOR PLAYER 2
                        continue;
                    }
                    first2 = Integer.parseInt(strike2.substring(0, 1));
                    second2 = strike2.substring(1).toUpperCase();
                    if (player1.strike(first2 - 1, second2) == 2) {
                        System.out.println("You have already fired at this spot, choose again!\n");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                        continue;
                    }
                    System.out.println();
                    if (p1.getNumbBoats() == 0) {
                        System.out.printf("\n%s wins!", p2Name);
                        break;
                    }
                    player = 1;
                } catch (Exception e) {
                    System.out.println("\nInvalid strike, try again!");
                    continue;
                }

            }

            if (player == 3) {


                while (true) {

//                    int[][] checkerBoard = new int[10][10];
//                    int evenOdd = 0;
//                    for (int j = 0; j < checkerBoard.length; j++) {
//                        for (int k = 0; k < checkerBoard[0].length; k++) {
//                            if (evenOdd % 2 == 0)
//                                checkerBoard[j][k] = 0;
//                            else
//                                checkerBoard[j][k] = 1;
//
//                        }
//                        evenOdd += 2;
//                    }
//
//                    while (true) {
//
//                        if (even) {
//                            computerFC = (int) (Math.random() * 10);
//                            computerSC = (int) (Math.random() * 10);
//                            if (player1.hitBoard[computerFC][computerSC].equals("H") || player1
//                                    .hitBoard[computerFC][computerSC].equals("M") ||
//                            checkerBoard[computerFC][computerSC] != 0)
//                                continue;
//                            else {
//                                even = false;
//                                break;
//                            }
//                        } else if (!even) {
//                            computerFC = (int) (Math.random() * 10);
//                            computerSC = (int) (Math.random() * 10);
//                            if (player1.hitBoard[computerFC][computerSC].equals("H") || player1
//                                    .hitBoard[computerFC][computerSC].equals("M") ||
//                            checkerBoard[computerFC][computerSC] != 1)
//                                continue;
//                            else {
//                                even = true;
//                                break;
//                            }
//                        }
//
//
//                    }
//
////
//                    String letterTest = alph[computerSC];
//                    System.out.println(letterTest);
//                    System.out.println(computerFC);
////                    if (player1.hitBoard[computerFC][computerSC].equals("H") || player1
////                            .hitBoard[computerFC][computerSC].equals("M")) {
////                        continue;
////                    }
//
//                    System.out.println("\nComputer is deciding where to strike... ");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                    }
//                    player1.strike(computerFC, letterTest);


                    computerFC = (int) (Math.random() * 10);
                    computerSC = (int) (Math.random() * 10);

                    String letter = alph[computerSC];
                    System.out.println(letter);
                    System.out.println(computerFC);
                    if (player1.hitBoard[computerFC][computerSC].equals("H") || player1
                            .hitBoard[computerFC][computerSC].equals("M")) {
                        continue;
                    }

                    System.out.println("\nComputer is deciding where to strike... ");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }

//                    computerFC = (int) (Math.random() * 10);
//                    computerSC = (int) (Math.random() * 10);

//                    String letter = alph[computerSC];
//                    System.out.println(letter);
//                    System.out.println(computerFC);

//                    if (player1.hitBoard[computerFC][computerSC].equals("H") || player1
//                            .hitBoard[computerFC][computerSC].equals("M")) {
//                        continue;
//                    }
//
//                    System.out.println("\nComputer is deciding where to strike... ");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                    }
//                    int strike = player1.strike(computerFC, letter);


                    int strike = player1.strike(computerFC, letter);

                    if (strike == 2) {
                        continue;
                    }

                    System.out.println("\n\n");


                    if (p1.getNumbBoats() == 0) {
                        System.out.printf("\n%s wins!", p2Name);
                        break;
                    }

                    player = 1;
                    break;

                }


            }


        }
    }
}
