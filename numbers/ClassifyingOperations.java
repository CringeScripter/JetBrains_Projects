package numbers;

import static numbers.NumberProperties.Property.*;

public class ClassifyingOperations {
    public static void classify(NumberProperties number) {
        buzz(number);
        duck(number);
        evenOdd(number);
        gapful(number);
        happySad(number);
        jumping(number);
        palindromic(number);
        spy(number);
        square(number);
        sunny(number);
    }


    private static void duck(NumberProperties number) {
        if (Long.toString(number.getNum()).contains("0")) {
            number.addProperty(DUCK);
        }
    }

    private static void evenOdd(NumberProperties number) {
        if (number.getNum() % 2 == 0) {
            number.addProperty(EVEN);
        } else {
            number.addProperty(ODD);
        }
    }

    private static void buzz(NumberProperties number) {
        if (number.getNum() % 7 == 0 || number.getNum() % 10 == 7) {
            number.addProperty(BUZZ);
        }
    }

    private static void palindromic(NumberProperties number) {
        StringBuilder stringNum = new StringBuilder(Long.toString(number.getNum()));
        if (stringNum.substring(0, (stringNum.length() / 2))
                .equals(stringNum.reverse().substring(0, stringNum.length() / 2))) {
            number.addProperty(PALINDROMIC);
        }
    }

    private static void gapful(NumberProperties number) {
        if (number.getNum() >= 100 &&
                number.getNum() % Long.parseLong(Long.toString(number.getNum()).substring(0, 1) + number.getNum() % 10) == 0) {
            number.addProperty(GAPFUL);
        }
    }

    private static void spy(NumberProperties number) {
        char[] digitsOfNum = Long.toString(number.getNum()).toCharArray();
        long sum = 0;
        long product = 1;
        for (char i : digitsOfNum) {
            sum += Long.parseLong("" + i);
            product *= Long.parseLong("" + i);
        }
        if (sum == product) {
            number.addProperty(SPY);
        }
    }

    public static void jumping(NumberProperties number) {
        boolean jumping = true;
        char[] digitsOfNum = Long.toString(number.getNum()).toCharArray();
        for (int i = 0; i < digitsOfNum.length - 1; i++) {
            if (Math.abs(digitsOfNum[i] - digitsOfNum[i + 1]) != 1) {
                jumping = false;
            }
        }
        if (jumping) number.addProperty(JUMPING);

    }

    private static boolean isSquare(long num) {
        return Math.sqrt(num) % 1 == 0;
    }

    private static void square(NumberProperties number) {
        if (isSquare(number.getNum())) {
            number.addProperty(SQUARE);
        }
    }

    private static void sunny(NumberProperties number) {
        if (isSquare(number.getNum() + 1)) {
            number.addProperty(SUNNY);
        }
    }

    private static void happySad(NumberProperties number) {
        long temp = number.getNum();
        long current;
        do {
            current = 0;
            int[] digits = Long.toString(temp).chars().map(c -> c - '0').toArray();
            for (int i : digits) {
                current += (long) i * i;
            }
            temp = current;
        } while (temp != 1 && temp != 4);
        if (temp == 1) {
            number.addProperty(HAPPY);
        } else {
            number.addProperty(SAD);
        }
    }
}
