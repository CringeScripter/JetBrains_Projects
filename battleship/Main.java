package battleship;

import static battleship.Gameplay.*;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        turnMove();
        Player player2 = new Player("Player 2");
        turnMove();
        do {
            displayFields(player1);
            shotFromTo(player1, player2);
            if (player2.shipsAlive == 0) break;
            turnMove();
            displayFields(player2);
            shotFromTo(player2, player1);
            if (player1.shipsAlive == 0) break;
            turnMove();
        } while (true);
    }
}
