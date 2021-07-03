package tictactoe;

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
            case "user":
                user2 = new Player('O');
                break;
            default:
                throw new IllegalArgumentException();

        }

        process(user1,user2,game);


    }

    private static void process(User user1, User user2, Game game) {
        GameCheckingOperations checker = new GameCheckingOperations();
        if (user2.symbol == 'X') {
            user2.makeMove(game);
        }
        do {
            user1.makeMove(game);
            checker.checkField(game);
            if (game.haveAWinner || game.underline_count == 0) break;
            user2.makeMove(game);
            checker.checkField(game);
        } while (!game.haveAWinner && game.underline_count != 0);
        if (!game.haveAWinner) {
            System.out.println("Draw");
        }
    }
}
