package numbers;

import java.util.Arrays;
import java.util.List;

import static numbers.NumberProperties.Property.*;
import static numbers.PrintCommands.printExcReason;
import static numbers.PrintCommands.printReason;

public class CheckingOperations {
    public static boolean isNatural(long num) {
        return num > 0;
    }

    public static boolean isValidFilter(String filter) {
        return Arrays.stream(NumberProperties.Property.values()).anyMatch(p -> p.name().equals(filter));
    }

    public static boolean isValidFilters(List<String> filters) {
        String invalidFilters = "";
        int numberOfInvFilters = 0;
        for (String filter : filters) {
            if (!isValidFilter(filter)) {
                invalidFilters += filter + ", ";
                numberOfInvFilters++;
            }
        }
        if (!invalidFilters.equals("")) {
            if (numberOfInvFilters == 1) {
                printReason(invalidFilters.substring(0, invalidFilters.length() - 2), 1);
            } else {
                printReason(invalidFilters.substring(0, invalidFilters.length() - 2), 2);
            }

            return false;
        }
        return true;
    }

    public static boolean isNotMutuallyExclusive(List<NumberProperties.Property> includingFilters, List<NumberProperties.Property> excludingFilters) {
        if (includingFilters.contains(SUNNY) && includingFilters.contains(SQUARE)) {
            printExcReason("SUNNY", "SQUARE");
            return false;
        }
        if (includingFilters.contains(EVEN) && includingFilters.contains(ODD)) {
            printExcReason("EVEN", "ODD");
            return false;
        }
        if (includingFilters.contains(SPY) && includingFilters.contains(DUCK)) {
            printExcReason("SPY", "DUCK");
            return false;
        }
        if (excludingFilters.contains(EVEN) && excludingFilters.contains(ODD)) {
            printExcReason("-EVEN", "-ODD");
            return false;
        }
        if (excludingFilters.contains(HAPPY) && excludingFilters.contains(SAD)) {
            printExcReason("-HAPPY", "-SAD");
            return false;
        }
        for (NumberProperties.Property filter : includingFilters) {
            if (excludingFilters.stream().anyMatch(filter::equals)) {
                printExcReason(filter.toString(), "-" + filter);
                return false;
            }
        }
        return true;
    }
}
