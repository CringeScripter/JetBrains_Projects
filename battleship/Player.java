package battleship;

public class Player {
    public Battlefield battlefield = new Battlefield();
    String playersName;
    int shipsAlive = 5;
    Fleet aircraftCarrier;
    Fleet battleship;
    Fleet submarine;
    Fleet cruiser;
    Fleet destroyer;

    public Player(String playersName) {
        battlefield.getField(battlefield.hisFieldStatus);
        System.out.println(playersName + ", place your ships on the game field");
        this.playersName = playersName;

        aircraftCarrier = new Fleet("Aircraft Carrier", 5, battlefield);
        battlefield.getField(battlefield.hisFieldStatus);

        battleship = new Fleet("Battleship", 4, battlefield);
        battlefield.getField(battlefield.hisFieldStatus);

        submarine = new Fleet("Submarine", 3, battlefield);
        battlefield.getField(battlefield.hisFieldStatus);

        cruiser = new Fleet("Cruiser", 3, battlefield);
        battlefield.getField(battlefield.hisFieldStatus);

        destroyer = new Fleet("Destroyer", 2, battlefield);
        battlefield.getField(battlefield.hisFieldStatus);
    }

}
