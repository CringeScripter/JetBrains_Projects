package numbers;

import java.util.List;

import static numbers.ClassifyingOperations.classify;


public class Processes {

    public static void process(long num) {
        NumberProperties current = new NumberProperties(num);
        classify(current);
        current.printInfo(1);
    }

    public static void process(long num, long range) {
        for (long current = num; current < num + range; current++) {
            NumberProperties number = new NumberProperties(current);
            classify(number);
            number.printInfo(2);
        }
    }

    public static void process(long num, long count, List<NumberProperties.Property> includingFilters, List<NumberProperties.Property> excludingFilters) {
        int counter = 0;
        while (counter < count) {
            NumberProperties number = new NumberProperties(num);
            classify(number);
            if (number.containsAll(includingFilters) && number.notContains(excludingFilters)) {
                number.printInfo(2);
                counter++;
            }
            num++;
        }
    }
}

