package battleship;

import static battleship.Battlefield.FieldSymbols.EMPTY;

public class Battlefield {

    char[][] hisFieldStatus = new char[10][10];
    char[][] opponentFieldStatus = new char[10][10];
    boolean[][] hisFieldVacant = new boolean[10][10];

    Battlefield() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                hisFieldStatus[x][y] = EMPTY;
                hisFieldVacant[x][y] = true;
                opponentFieldStatus[x][y] = EMPTY;
            }
        }
    }

    protected void getField(char[][] field) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) (65 + i));
            for (int j = 0; j < 10; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
    }

    protected void setVacancies(int cellX, int cellY) {
        for (int x = cellX - 1; x <= cellX + 1; x++) {
            for (int y = cellY - 1; y <= cellY + 1; y++) {
                try {
                    hisFieldVacant[x][y] = false;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    static class FieldSymbols {
        public static final char SHIP = 'O';
        public static final char HIT = 'X';
        public static final char MISS = 'M';
        public static final char EMPTY = '~';
    }
}

