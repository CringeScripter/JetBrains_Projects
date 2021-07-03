package numbers;

import java.util.ArrayList;
import java.util.List;

public class NumberProperties {
    private final long num;
    private final List<Property> properties = new ArrayList<>();

    public NumberProperties(long num) {
        this.num = num;
    }

    public long getNum() {
        return num;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void printInfo(int index) {
        switch (index) {
            case 1:
                System.out.println("Properties of " + num + "\n"
                        + "even: " + properties.contains(Property.EVEN) + "\n"
                        + "odd: " + properties.contains(Property.ODD) + "\n"
                        + "buzz: " + properties.contains(Property.BUZZ) + "\n"
                        + "duck: " + properties.contains(Property.DUCK) + "\n"
                        + "palindromic: " + properties.contains(Property.PALINDROMIC) + "\n"
                        + "gapful: " + properties.contains(Property.GAPFUL) + "\n"
                        + "spy: " + properties.contains(Property.SPY) + "\n"
                        + "sunny: " + properties.contains(Property.SUNNY) + "\n"
                        + "square: " + properties.contains(Property.SQUARE) + "\n"
                        + "jumping: " + properties.contains(Property.JUMPING) + "\n"
                        + "happy: " + properties.contains(Property.HAPPY) + "\n"
                        + "sad:" + properties.contains(Property.SAD));
                break;
            case 2:
                System.out.println(num + " is " + properties.toString().substring(1, properties.toString().length() - 1).toLowerCase());
                break;
        }
    }

    public boolean contains(Property filter) {
        return properties.contains(filter);
    }

    public boolean containsAll(List<Property> filters) {
        return properties.containsAll(filters);
    }

    public boolean notContains(List<Property> filters) {
        return properties.stream().noneMatch(filters::contains);
    }

    enum Property {BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE, JUMPING, HAPPY, SAD}


}
