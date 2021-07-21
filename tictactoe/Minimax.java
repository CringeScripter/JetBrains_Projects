package tictactoe;

public class Minimax {
    private final Game game;
    private final char minimizingSymbol;
    private final char maximizingSymbol;

    public Minimax(Game game, char minimizingSymbol, char maximizingSymbol) {
        this.game = game;
        this.minimizingSymbol = minimizingSymbol;
        this.maximizingSymbol = maximizingSymbol;
    }

    public Coordinates bestMove() {
        int bestScore = Integer.MIN_VALUE;
        Coordinates best = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.field[i][j] == '_') {
                    game.field[i][j] = maximizingSymbol;
                    int score = minimax( 0,false);
                    game.field[i][j] = '_';
                    if (score > bestScore) {
                        bestScore = score;
                        best = new Coordinates(i, j);
                    }
                }
            }

        }
        return best;
    }

    private int minimax(int depth, boolean isMaximizing) {
        GameCheckingOperations.checkField(game);
        if (game.haveAWinner) {
            int score = game.winner == maximizingSymbol ? 10 : -10;
            game.winner = '_';
            game.haveAWinner = false;
            return score;
        }
        if (GameCheckingOperations.isDraw(game)) {
            return 0;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.field[i][j] == '_') {
                        game.field[i][j] = maximizingSymbol;
                        int score = minimax(depth+1, false);
                        game.field[i][j] = '_';
                        bestScore = Math.max(score,bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (game.field[i][j] == '_') {
                        game.field[i][j] = minimizingSymbol;
                        int score = minimax(depth+1, true);
                        game.field[i][j] = '_';
                        bestScore = Math.min(score,bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
