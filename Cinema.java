package cinema;

import java.util.Scanner;

public class Cinema {
    Scanner sc = new Scanner(System.in);
    boolean exitSign = false;
    int rowsCount;
    int seatsCount;
    char[][] seats;

    int purchasedCount = 0;
    float percentage = 0f;
    int income = 0;
    int totalIncome;

    Cinema(int rowsCount, int seatsCount) {
        totalIncome = (rowsCount * seatsCount > 60 ?
                (10 * seatsCount * (rowsCount / 2)) + (8 * (rowsCount - (rowsCount / 2)) * seatsCount)
                : 10 * rowsCount * seatsCount);
        this.seatsCount = seatsCount;
        this.rowsCount = rowsCount;
        this.seats = new char[rowsCount][seatsCount];
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < seatsCount; j++) {
                setSeat(i, j, 'S');
            }
        }
    }

    public static void main(String[] args) {
        Cinema room = generateRoom();
        do {
            room.showMenu();
            room.action(room.sc.nextInt());
        } while (!room.exitSign);
    }

    public static Cinema generateRoom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        return new Cinema(rows, seats);
    }

    private void action(int choice) {
        switch (choice) {
            case 0:
                exitSign = true;
                break;
            case 1:
                getCinema();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                showStat();
                break;
            default:
                System.out.println("Unknown command\n");
                break;
        }
    }

    private void buyTicket() {
        System.out.println("Enter a row number:");
        int row = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = sc.nextInt();
        if (row > rowsCount || seat > seatsCount) {
            System.out.println("Wrong input!\n");
            return;
        }

        switch (seats[row - 1][seat - 1]) {
            case 'B':
                System.out.println("That ticket has already been purchased!\n");
                buyTicket();
                break;

            case 'S':
                setSeat(row - 1, seat - 1, 'B');
                if (rowsCount * seatsCount <= 60) {
                    System.out.println("Ticket price: $" + 10 + "\n");
                    income += 10;
                } else {
                    System.out.println(row <= rowsCount / 2 ? "Ticket price: $" + 10 : "Ticket price: $" + 8 + "\n");
                    income += row <= rowsCount / 2 ? 10 : 8;
                }
                purchasedCount++;
                percentage = 100f * (float) purchasedCount / (float) rowsCount / (float) seatsCount;
                break;
        }
    }

    private void showMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n");
    }

    private void showStat() {
        System.out.format("Number of purchased tickets: %d\n" +
                        "Percentage: %.2f%c\n" +
                        "Current income: $%d\n" +
                        "Total income: $%d\n" +
                        "\n",
                purchasedCount,
                percentage, '\u0025',
                income,
                totalIncome);
    }

    private void getCinema() {
        System.out.print("Cinema:\n  ");

        for (int i = 0; i < seatsCount; i++) {
            System.out.print((i + 1) + " ");
        }

        System.out.println();

        for (int i = 0; i < rowsCount; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < seatsCount; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void setSeat(int row, int seat, char status) {
        seats[row][seat] = status;
    }
}