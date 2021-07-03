package battleship;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static battleship.Battlefield.FieldSymbols.*;

public class Gameplay {

    public static void turnMove() {
        System.out.println("Press Enter and pass the move to another player\n" + "...");
        try {
            System.in.read();
        } catch (IOException ignored) {
        }

    }

    public static void displayFields(Player player) {
        player.battlefield.getField(player.battlefield.opponentFieldStatus);
        System.out.println("---------------------");
        player.battlefield.getField(player.battlefield.hisFieldStatus);
    }

    public static void shotFromTo(Player fromPlayer, Player toPlayer) {
        System.out.println(fromPlayer.playersName + ", it's your turn:");
        int[] coordinates = getCoordinates();

        int x = coordinates[0];
        int y = coordinates[1];

        switch (toPlayer.battlefield.hisFieldStatus[y][x]) {
            case SHIP:
                executeHit(fromPlayer, toPlayer, x, y);
                break;
            case HIT:
                System.out.println("You hit a ship!");
                break;
            case EMPTY:
                executeMiss(fromPlayer, toPlayer, x, y);
                break;
            case MISS:
                System.out.println("You missed!");
                break;
        }
    }

    private static void executeMiss(Player fromPlayer, Player toPlayer, int x, int y) {
        fromPlayer.battlefield.opponentFieldStatus[y][x] = MISS;
        toPlayer.battlefield.hisFieldStatus[y][x] = MISS;
        System.out.println("You missed!");
    }

    private static void executeHit(Player fromPlayer, Player toPlayer, int x, int y) {
        fromPlayer.battlefield.opponentFieldStatus[y][x] = HIT;
        toPlayer.battlefield.hisFieldStatus[y][x] = HIT;
        Objects.requireNonNull(findHitShip(toPlayer, x, y)).health--;
        if (Objects.requireNonNull(findHitShip(toPlayer, x, y)).health == 0) {
            toPlayer.shipsAlive--;
            System.out.println(toPlayer.shipsAlive == 0 ?
                    "You sank the last ship. You won. Congratulations!"
                    : "You sank a ship!");
        } else {
            System.out.println("You hit a ship!");
        }
    }


    private static Fleet findHitShip(Player toPlayer, int x, int y) {
        String point = x + "" + y;
        if (toPlayer.aircraftCarrier.shipsCells.contains(point)) {
            return toPlayer.aircraftCarrier;
        }
        if (toPlayer.battleship.shipsCells.contains(point)) {
            return toPlayer.battleship;
        }
        if (toPlayer.submarine.shipsCells.contains(point)) {
            return toPlayer.submarine;
        }
        if (toPlayer.cruiser.shipsCells.contains(point)) {
            return toPlayer.cruiser;
        }
        if (toPlayer.destroyer.shipsCells.contains(point)) {
            return toPlayer.destroyer;
        }
        return null;
    }

    private static int[] getCoordinates() {
        Scanner sc = new Scanner(System.in);
        int x;
        int y;
        do {
            String coordinate = sc.next().toUpperCase();
            x = Integer.parseInt(coordinate.substring(1)) - 1;
            y = coordinate.charAt(0) - 65;
            if (!(x > -1 && x < 10 && y > -1 && y < 10)) {
                System.out.println("Error! Wrong coordinates! Try again:");
            }
        } while (!(x > -1 && x < 10 && y > -1 && y < 10));
        return new int[]{x, y};
    }
}
