package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static numbers.CheckingOperations.*;
import static numbers.PrintCommands.*;
import static numbers.Processes.process;

public class Main {

    public static void main(String[] args) {
        printHello();
        new Main().running();
    }

    public void running() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("0")) {
            if (input.isEmpty()) {
                printInstruction();
                running();
                return;
            }
            String[] stringInput = input.split(" ");
            try {
                switch (stringInput.length) {
                    case 1:
                        long num1 = Long.parseLong(stringInput[0]);
                        if (isNatural(num1)) {
                            process(num1);
                        } else {
                            printReason(1);
                        }
                        break;
                    case 2:
                        long num2 = Long.parseLong(stringInput[0]);
                        long range = Long.parseLong(stringInput[1]);
                        if (isNatural(num2) && isNatural(range)) {
                            process(num2, range);
                        } else {
                            if (!isNatural(num2)) {
                                printReason(1);
                            }
                            if (!isNatural(range)) {
                                printReason(2);
                            }
                        }
                        break;
                    default:
                        long num4 = Long.parseLong(stringInput[0]);
                        long count2 = Long.parseLong(stringInput[1]);
                        List<String> includingFilters = new ArrayList<>();
                        List<String> excludingFilters = new ArrayList<>();
                        for (int i = 2; i < stringInput.length; i++) {
                            if (stringInput[i].charAt(0) == '-') {
                                excludingFilters.add(stringInput[i].replaceAll("-", "").toUpperCase());
                            } else {
                                includingFilters.add(stringInput[i].toUpperCase());
                            }
                        }
                        if (isValidFilters(includingFilters) && isValidFilters(excludingFilters)) {
                            List<NumberProperties.Property> incFilters = includingFilters.stream()
                                    .map(NumberProperties.Property::valueOf).collect(Collectors.toList());
                            List<NumberProperties.Property> excFilters = excludingFilters.stream()
                                    .map(NumberProperties.Property::valueOf).collect(Collectors.toList());
                            if (isNotMutuallyExclusive(incFilters, excFilters)) {
                                process(num4, count2, incFilters, excFilters);
                            }
                        }
                }
            } catch (NumberFormatException e) {
                System.out.println("The first parameter should be a natural number or zero.");
            }
            System.out.println("\nEnter a request:");
            input = sc.nextLine();
        }
        System.out.println("Goodbye!");
    }
}


