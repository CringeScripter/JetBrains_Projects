package tictactoe;
import static tictactoe.GameCheckingOperations.*;
public class Gameplay {
    public static void prepare(String parameters) {

        Game game = new Game();
        game.printField();
        String[] players = parameters.split(" ");
        User user1 ;
        User user2 ;

        switch (players[0]) {
            case "easy":
                user1 = new EasyBot('X');
                break;
            case "medium":
                user1 = new MediumBot('X');
                break;
            case "hard":
                user1 = new HardBot('X');
                break;
            case "user":
                user1 = new Player('X');
                break;
            default:
                throw new IllegalArgumentException();
        }
        switch (players[1]) {
            case "easy":
                user2 = new EasyBot('O');
                break;
            case "medium":
                user2 = new MediumBot('O');
                break;
            case "hard":
                user2 = new HardBot('O');
                break;
            case "user":
                user2 = new Player('O');
                break;
            default:
                throw new IllegalArgumentException();

        }

        process(user1,user2,game);


    }

    private static void process(User user1, User user2, Game game) {

        if (user2.symbol == 'X') {
            user2.makeMove(game);
        }
        do {
            user1.makeMove(game);
            checkField(game);
            checkEnding(game);
            if (game.haveAWinner || isDraw(game)) break;
            user2.makeMove(game);
            checkField(game);
            checkEnding(game);
        } while (!game.haveAWinner && !isDraw(game));
        if (!game.haveAWinner) {
            System.out.println("Draw");
        }
    }
}