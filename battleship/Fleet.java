package battleship;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static battleship.Battlefield.FieldSymbols.SHIP;

public class Fleet {
    public List<String> shipsCells = new ArrayList<String>();
    String name;
    int length;
    int health;
    int x1;
    int y1;
    int x2;
    int y2;

    Fleet(String name, int length, Battlefield battlefield) {
        System.out.println("Enter the coordinates of the " + name + " (" + length + " cells):");
        this.name = name;
        this.length = length;
        health = length;
        setCoordinates(battlefield);
        setShip(battlefield);
        setShipsCells();
    }

    private void setCoordinates(Battlefield battlefield) {
        Scanner sc = new Scanner(System.in);
        String firstPoint = sc.next().toUpperCase();
        String secondPoint = sc.next().toUpperCase();
        int x1 = Integer.parseInt(firstPoint.substring(1) + "") - 1;
        int y1 = firstPoint.charAt(0) - 65;
        int x2 = Integer.parseInt(secondPoint.substring(1) + "") - 1;
        int y2 = secondPoint.charAt(0) - 65;
        this.x1 = Integer.min(x1, x2);
        this.x2 = Integer.max(x1, x2);
        this.y1 = Integer.min(y1, y2);
        this.y2 = Integer.max(y1, y2);
        if (!isHorizontal() && !isVertical() ||
                x1 > 10 || x2 > 10 || y1 > 10 || y2 > 10) {
            System.out.println("Error! Wrong ship location! Try again:");
            setCoordinates(battlefield);
            return;
        }
        if (!isRightLength()) {
            System.out.println("Error! Wrong length of the " + name + "! Try again:");
            setCoordinates(battlefield);
            return;
        }
        if (!isVacant(battlefield)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            setCoordinates(battlefield);
        }

    }

    private void setShip(Battlefield battlefield) {

        for (int y = x1; y <= x2; y++) {
            for (int x = y1; x <= y2; x++) {
                battlefield.hisFieldStatus[x][y] = SHIP;
                battlefield.setVacancies(y, x);
            }
        }
    }

    private void setShipsCells() {
        if (isHorizontal()) {
            for (int x = x1; x <= x2; x++) {
                shipsCells.add(x + "" + y1);
            }
        } else {
            for (int y = y1; y <= y2; y++) {
                shipsCells.add(x1 + "" + y);
            }
        }
    }


    private boolean isVacant(Battlefield battlefield) {
        return battlefield.hisFieldVacant[x1][y1] && battlefield.hisFieldVacant[x2][y2];
    }

    private boolean isRightLength() {
        return Math.abs((x1 - x2) + (y1 - y2)) + 1 == length;
    }

    private boolean isHorizontal() {
        return y1 == y2;
    }

    private boolean isVertical() {
        return x1 == x2;
    }
}
