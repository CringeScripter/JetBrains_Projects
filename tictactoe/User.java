package tictactoe;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static tictactoe.GameCheckingOperations.isValidCoordinates;

public abstract class User {
    protected char symbol;


    protected User(char symbol) {
        this.symbol = symbol;
    }

    abstract void makeMove(Game game);

}

class Player extends User {
    Scanner sc = new Scanner(System.in);

    public Player(char symbol) {
        super(symbol);
    }

    public void makeMove(Game game) {
        setCell(game);
        game.printField();
    }

    private Coordinates readCoordinates() {
        try {
            int i = Integer.parseInt(sc.next());
            int j = Integer.parseInt(sc.next());
            return new Coordinates(i, j);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!\n" + "Enter the coordinates: ");
            return readCoordinates();
        }
    }

    private void setCell(Game game) {
        System.out.println("Enter the coordinates: ");
        Coordinates selected = readCoordinates();

        int i = selected.getI();
        int j = selected.getJ();

        if (i > 3 || j > 3 || i < 1 || j < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            setCell(game);
            return;
        }

        i--;
        j--;

        if (isValidCoordinates(i, j, game)) {
            game.field[i][j] = symbol;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            setCell(game);
        }
    }
}

class EasyBot extends User {
    public EasyBot(char symbol) {
        super(symbol);
    }

    protected void makeRandomMove(Game game) {
        Coordinates selected;
        do {
            selected = generateCoordinates();
        } while (!isValidCoordinates(selected.getI(), selected.getJ(), game));
        game.field[selected.getI()][selected.getJ()] = symbol;
        game.printField();
    }

    protected Coordinates generateCoordinates() {
        return new Coordinates(ThreadLocalRandom.current().nextInt(3),
                ThreadLocalRandom.current().nextInt(3));
    }

    public void makeMove(Game game) {
        System.out.println("Making move level \"easy\"");
        makeRandomMove(game);
    }


}


class MediumBot extends EasyBot {
    boolean madeMove = false;

    public MediumBot(char symbol) {
        super(symbol);
    }

    public void makeMove(Game game) {
        madeMove = false;
        System.out.println("Making move level \"medium\"");
        char enemySymbol = symbol == 'X' ? 'O' : 'X';
        checkHorizontal(game, symbol);
        if (madeMove) return;
        checkVertical(game, symbol);
        if (madeMove) return;
        checkDiagonals(game, symbol);
        if (madeMove) return;
        checkHorizontal(game, enemySymbol);
        if (madeMove) return;
        checkVertical(game, enemySymbol);
        if (madeMove) return;
        checkDiagonals(game, enemySymbol);
        if (madeMove) return;
        super.makeRandomMove(game);
    }

    private void checkHorizontal(Game game, char symb) {
        int selectedI = -1;
        int selectedJ = -1;
        int symbolCounter = 0;
        int emptyCellCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.field[i][j] == symb) symbolCounter++;
                if (game.field[i][j] == '_') {
                    emptyCellCounter++;
                    selectedI = i;
                    selectedJ = j;
                }
            }
            if (symbolCounter == 2 && emptyCellCounter == 1) {
                game.field[selectedI][selectedJ] = symbol;
                game.printField();
                madeMove = true;
            }
            symbolCounter = 0;
            emptyCellCounter = 0;
        }

    }

    private void checkVertical(Game game, char symb) {
        int selectedI = -1;
        int selectedJ = -1;
        int symbolCounter = 0;
        int emptyCellCounter = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (game.field[i][j] == symb) symbolCounter++;
                if (game.field[i][j] == '_') {
                    emptyCellCounter++;
                    selectedI = i;
                    selectedJ = j;
                }
            }
            if (symbolCounter == 2 && emptyCellCounter == 1) {
                game.field[selectedI][selectedJ] = symbol;
                game.printField();
                madeMove = true;
            }
            symbolCounter = 0;
            emptyCellCounter = 0;
        }

    }

    private void checkDiagonals(Game game, char symb) {
        if (game.field[0][0] == symb && game.field[1][1] == symb && game.field[2][2] == '_') {
            game.field[2][2] = symbol;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][0] == symb && game.field[1][1] == '_' && game.field[2][2] == symb) {
            game.field[1][1] = symbol;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][0] == '_' && game.field[1][1] == symb && game.field[2][2] == symb) {
            game.field[0][0] = symbol;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == symb && game.field[1][1] == symb && game.field[2][0] == '_') {
            game.field[2][0] = symbol;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == symb && game.field[1][1] == '_' && game.field[2][0] == symb) {
            game.field[1][1] = symbol;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == '_' && game.field[1][1] == symb && game.field[2][0] == symb) {
            game.field[0][2] = symbol;
            game.printField();
            madeMove = true;
        }

    }
}
class HardBot extends User {

    public HardBot(char symbol) {
        super(symbol);
    }

    @Override
    void makeMove(Game game) {
        System.out.println("Making move level \"hard\"");
        Minimax minimax = new Minimax(game, symbol == 'X' ? 'O' : 'X', symbol);
        Coordinates bestPosition = minimax.bestMove();
        game.field[bestPosition.getI()][bestPosition.getJ()] = symbol;
        game.printField();
    }

}
