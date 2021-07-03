package numbers;


import java.util.Arrays;

public class PrintCommands {
    public static void printHello() {
        System.out.println("Welcome to Amazing Numbers!");
        printInstruction();
    }

    public static void printReason(int index) {
        switch (index) {
            case 1:
                System.out.println("The first parameter should be a natural number or zero.");
                break;
            case 2:
                System.out.println("The second parameter should be a natural number.");
                break;

        }
    }

    public static void printReason(String filter, int index) {
        switch (index) {
            case 1:
                System.out.println("The property [" + filter.toUpperCase() + "] is wrong.\n" +
                        "Available properties: " + Arrays.toString(NumberProperties.Property.values()));
                break;
            case 2:
                System.out.println("The properties [" + filter + "] are wrong.\n" +
                        "Available properties: " + Arrays.toString(NumberProperties.Property.values()));
        }

    }


    public static void printExcReason(String filter1, String filter2) {
        System.out.println("The request contains mutually exclusive properties: [" + filter1 + ", " + filter2 + "]\n" +
                "There are no numbers with these properties.");
    }


    public static void printInstruction() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit." +
                "\n\nEnter a request:");
    }
}
