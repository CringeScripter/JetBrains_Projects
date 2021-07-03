package tictactoe;

import java.util.Scanner;
import java.util.Stack;

import static tictactoe.GameCheckingOperations.isValidCoordinates;

public  class User {
    protected char symbol;
    protected int selectedI;
    protected int selectedJ;

    void makeMove(Game game){};
}

class Player extends User {
    Scanner sc = new Scanner(System.in);

    public Player(char symbol) {
        this.symbol=symbol;
    }

    public void makeMove(Game game) {
        setCell(game);
        game.printField();
        game.underline_count--;
    }

    private void setInt() {
        try {
            selectedI = Integer.parseInt(sc.next());
            selectedJ = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!\n" + "Enter the coordinates: ");
            setInt();
        }
    }

    private void setCell(Game game) {
        System.out.println("Enter the coordinates: ");
        setInt();

        if (selectedI > 3 || selectedJ > 3 || selectedI < 1 || selectedJ < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            setCell(game);
            return;
        }
        selectedI--;
        selectedJ--;

        if (isValidCoordinates(selectedI, selectedJ, game)) {
            game.field[selectedI][selectedJ] = symbol;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            setCell(game);
        }
    }
}

class EasyBot extends User {
    public EasyBot(char symbol) {
        this.symbol=symbol;
    }

    protected void makeRandomMove(Game game) {
        do {
            generateCoordinates();
        } while (!isValidCoordinates(selectedI, selectedJ, game));
        game.field[selectedI][selectedJ] = symbol;
        game.underline_count--;
        game.printField();
    }

    protected void generateCoordinates() {
        selectedI = (int) (Math.random() * 3);
        selectedJ = (int) (Math.random() * 3);
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
        int symbolCounter = 0;
        int emptyCellCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.field[i][j] == symb) symbolCounter++;
                if (game.field[i][j] == '_') {
                    emptyCellCounter++;
                    this.selectedI = i;
                    this.selectedJ = j;
                }
            }
            if (symbolCounter == 2 && emptyCellCounter == 1) {
                game.field[selectedI][selectedJ] = symbol;
                game.underline_count--;
                game.printField();
                madeMove = true;
            }
            symbolCounter = 0;
            emptyCellCounter = 0;
        }

    }

    private void checkVertical(Game game, char symb) {
        int symbolCounter = 0;
        int emptyCellCounter = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (game.field[i][j] == symb) symbolCounter++;
                if (game.field[i][j] == '_') {
                    emptyCellCounter++;
                    this.selectedI = i;
                    this.selectedJ = j;
                }
            }
            if (symbolCounter == 2 && emptyCellCounter == 1) {
                game.field[selectedI][selectedJ] = symbol;
                game.underline_count--;
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
            game.underline_count--;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][0] == symb && game.field[1][1] == '_' && game.field[2][2] == symb) {
            game.field[1][1] = symbol;
            game.underline_count--;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][0] == '_' && game.field[1][1] == symb && game.field[2][2] == symb) {
            game.field[0][0] = symbol;
            game.underline_count--;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == symb && game.field[1][1] == symb && game.field[2][0] == '_') {
            game.field[2][0] = symbol;
            game.underline_count--;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == symb && game.field[1][1] == '_' && game.field[2][0] == symb) {
            game.field[1][1] = symbol;
            game.underline_count--;
            game.printField();
            madeMove = true;
        }
        if (game.field[0][2] == '_' && game.field[1][1] == symb && game.field[2][0] == symb) {
            game.field[0][2] = symbol;
            game.underline_count--;
            game.printField();
            madeMove = true;
        }

    }


}