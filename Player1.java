/**
 * Created by Andrew Peterson on 11/1/2015.
 */
public class Player1 {

    private Boat[] boats = new Boat[5];
    private int lv;
    private String name;
    private int numbBoats = 0;     //Should eventually be 5

    public Player1(String name) {
        lv = 0;
        this.name = name;
    }

    public static void main(String[] args) {

        Player1 p1 = new Player1("Andrew");
        Board b = new Board(p1);
        Player1 p2 = new Player1("Matt");
        Board b2 = new Board(p2);
        //System.out.println("" + p1.canSetBoat("AIRCRAFT_CARRIER", 5, 5, 3, "down"));
        //System.out.println("" + p1.canSetBoat("BATTLESHIP", 4, 2, 5, "down"));

        System.out.println(p1.getName());
        System.out.println(p1.getNumbBoats());


        b2.setBoat(5, 3, "B", "right", "AIRCRAFT_CARRIER");
        //b2.getBoard();
        //System.out.println("\n");


        b.setBoat(5, 5, "D", "down", "AIRCRAFT_CARRIER");
        b.setBoat(4, 2, "F", "down", "BATTLESHIP");
        b.setBoat(3, 1, "B", "right", "SUBMARINE");

        System.out.println("\n");
        b.getBoard();
        b.strike(7, "D");//called by current player
        b.getBoard();    //dislayed to b2
        b2.strike(3, "D");
        b2.getBoard();
        b.strike(3, "F");
        b.getBoard();
        b2.strike(2, "D");
        b2.getBoard();
        b.strike(9, "D");
        b.getBoard();

//        b.getBoard();
//        System.out.println();
//        b.strike(7, "D");
//        System.out.println();
//        b.getBoard();
        //System.out.println(p1.getBoatByName("AIRCRAFT_CARRIER"));


    }

    public void addBoat(Boat b) {
        boats[lv] = b;
        numbBoats++;
        lv++;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getNumbBoats() {
        return numbBoats;
    }

    public int updateNumbBoats() {
        return numbBoats--;
    }

    public Boat[] getBoats() {
        Boat[] res = new Boat[numbBoats];
        for (int i = 0; i < boats.length; i++) {
            if (boats[i] != null) {
                res[i] = boats[i];
            }
        }
        return res;
    }

    public Boat getBoatByName(String name) {
        Boat[] boats1 = getBoats();
        for (int i = 0; i < boats1.length; i++) {
            if (boats1[i].getBoatName().equals(name))
                return boats1[i];
        }
        return null;
    }


}
