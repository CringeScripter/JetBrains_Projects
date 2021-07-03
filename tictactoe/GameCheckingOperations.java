package tictactoe;

public class GameCheckingOperations {
    public static boolean isValidCoordinates(int i, int j, Game game) {
        return game.field[i][j] == '_';
    }

    public void checkField(Game game) {
        checkHorizontal(game);
        checkVertical(game);
        checkDiagonal(game);
        checkEnding(game);
    }


    private void checkHorizontal(Game game) {
        int control = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (game.field[i][j] != '_' && game.field[i][j] == game.field[i][j + 1]) {
                    control++;
                }
                if (control == 3) {
                    game.winner = game.field[i][j];
                    game.haveAWinner = true;
                    return;
                }
            }
            control = 1;
        }
    }

    private void checkVertical(Game game) {
        int control = 1;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 2; i++) {
                if (game.field[i][j] != '_' && game.field[i][j] == game.field[i + 1][j]) {
                    control++;
                }
                if (control == 3) {
                    game.winner = game.field[i][j];
                    game.haveAWinner = true;
                    return;
                }
            }
            control = 1;
        }
    }

    private void checkDiagonal(Game game) {
        if (game.field[0][0] != '_' && game.field[0][0] == game.field[1][1] && game.field[1][1] == game.field[2][2]) {
            game.winner = game.field[0][0];
            game.haveAWinner = true;
            return;
        }
        if (game.field[0][2] != '_' && game.field[0][2] == game.field[1][1] && game.field[1][1] == game.field[2][0]) {
            game.winner = game.field[0][2];
            game.haveAWinner = true;

        }
    }

    private void checkEnding(Game game) {
        if (game.haveAWinner) {
            System.out.println(game.winner + " wins");
        }
    }
}



