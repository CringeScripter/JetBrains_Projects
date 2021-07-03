package tictactoe;

import java.util.Scanner;

import static tictactoe.Gameplay.prepare;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] commands;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Input command:");
            getCommand(sc.nextLine());
            switch (commands[0]) {
                case "start":
                    if (commands.length < 3) {
                        System.out.println("Bad parameters!");
                        break;
                    }
                    prepare(commands[1] + " " + commands[2]);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Bad parameters!");
            }
        }
    }

    public static void getCommand(String command) {
        commands = command.split(" ");
    }
}
