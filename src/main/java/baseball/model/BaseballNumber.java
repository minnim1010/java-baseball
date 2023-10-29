package baseball.model;

import baseball.validator.BaseballValidator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseballNumber {

    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 9;

    private static final Map<Integer, BaseballNumber> intByBaseballNumber = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
            .boxed()
            .collect(Collectors.toMap(i -> i, BaseballNumber::new));
    private final int number;

    private BaseballNumber(int number) {
        this.number = number;
    }

    public static BaseballNumber from(int number) {
        BaseballValidator.validateWithinBounds(number);

        return intByBaseballNumber.get(number);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseballNumber that = (BaseballNumber) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
