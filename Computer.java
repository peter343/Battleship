/**
 * Created by Andrew Peterson on 11/8/2015.
 */
public class Computer {

    Player1 computer;
    Board board;
    boolean hit = false;
    int surround = 4;

    public Computer(Player1 player, Board board) {
        computer = player;
        this.board = board;
    }

    public boolean strikeOpponent(int row, int col) {

        if (!hit) {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
//            String letter = alph[secondCoord];
//            System.out.println(letter);
//            System.out.println(firstCoor);
//            if (player2.hitBoard[firstCoor][secondCoord].equals("H") || player2
//                    .hitBoard[firstCoor][secondCoord].equals("M")) {
//
//                continue;
//            }
            return false;
        }

        return false;
    }


}
