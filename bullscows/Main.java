package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    String code = "";
    int cows;
    int bulls;
    int length;
    int symbolsCount;
    boolean errSign = false;
    Status status = Status.LENGTH;

    public static void main(String[] args) {
        Main game = new Main();
        System.out.println("Please, enter the secret code's length:");
        do {
            if (game.errSign) {
                return;
            }
            game.process(game.sc.next());
        } while (game.bulls < game.length);
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private void process(String yourType) {
        switch (status) {
            case LENGTH:
                setLength(yourType);
                break;
            case GENCODE:
                setLength(yourType);
                if (errSign) return;
                generateCode();
                break;
            case GAME:
                step(yourType);
                break;
        }
    }

    private void setLength(String yourType) {
        switch (status) {
            case LENGTH:
                if (!yourType.matches("\\d+")) {
                    System.out.println("Error: \"" + yourType + "\" isn't a valid number.");
                    errSign = true;
                    return;
                }
                length = Integer.parseInt(yourType);

                if (length > 36 || length == 0) {
                    System.out.println("Error: can't generate a secret number with a length of " + length + " because there aren't enough unique symbols.");
                    errSign = true;
                    return;
                }

                System.out.println("Input the number of possible symbols in the code:");
                status = Status.GENCODE;
                break;

            case GENCODE:
                symbolsCount = Integer.parseInt(yourType);

                if (symbolsCount < length) {
                    System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + symbolsCount + " unique symbols.");
                    errSign = true;
                    return;
                }
                if (symbolsCount > 36) {
                    System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                    errSign = true;
                    return;
                }
                break;
        }
    }

    private void generateCode() {
        Random rnd = new Random();

        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";

        int i = 0;

        StringBuilder sbCode = new StringBuilder();

        do {
            char currChar = symbols.charAt(rnd.nextInt(symbolsCount));
            if (!sbCode.toString().contains("" + currChar)) {
                sbCode.append(currChar);
                i++;
            }

        } while (i < length);
        code = sbCode.toString();
        System.out.print("The secret is prepared: ");
        for (int q = 0; q < length; q++) {
            System.out.print("*");
        }
        System.out.println(symbolsCount > 10 ? " (0-9, a-" + symbols.charAt(symbolsCount - 1) + ")."
                : " (0-" + symbols.charAt(symbolsCount - 1) + ").");
        status = Status.GAME;
    }

    private void step(String yourType) {
        cows = 0;
        bulls = 0;
        for (int i = 0; i < code.length(); i++) {
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(i) == yourType.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        System.out.println(cows == 0 && bulls == 0 ? "Grade: None. " :
                cows != 0 && bulls != 0 ? "Grade: " + bulls + " bull(s) and " + cows + " cow(s). " :
                        cows != 0 && bulls == 0 ? "Grade: " + cows + " cow(s). " :
                                "Grade: " + bulls + " bull(s). ");

    }

    enum Status {
        LENGTH, GAME, GENCODE;
    }

}