package baseball.model;

import baseball.validator.BaseballValidator;
import java.util.List;
import java.util.stream.IntStream;

public class Baseball {

    public static final int LENGTH = 3;

    private final List<BaseballNumber> baseballNumbers;

    private Baseball(List<BaseballNumber> baseballNumbers) {
        BaseballValidator.validateLength(baseballNumbers);
        BaseballValidator.validateUnique(baseballNumbers);

        this.baseballNumbers = baseballNumbers.stream().toList();
    }

    public static Baseball from(List<BaseballNumber> baseballNumbers) {
        return new Baseball(baseballNumbers);
    }

    public List<BaseballNumber> getBaseballNumbers() {
        return baseballNumbers;
    }

    public BaseballNumber get(final int index) {
        return baseballNumbers.get(index);
    }

    public boolean contains(final BaseballNumber baseballNumber) {
        return baseballNumbers.contains(baseballNumber);
    }

    public int getStrikeCount(final Baseball compare) {
        return (int) IntStream.range(0, Baseball.LENGTH)
                .filter(i -> isStrike(compare, i))
                .count();
    }

    private boolean isStrike(Baseball compare, int idx) {
        return this.get(idx).equals(compare.get(idx));
    }

    public int getBallCount(final Baseball compare) {
        return (int) IntStream.range(0, Baseball.LENGTH)
                .filter(i -> isBall(compare, i))
                .count();
    }

    private boolean isBall(Baseball compare, int idx) {
        BaseballNumber compareNumber = compare.get(idx);
        return !this.get(idx).equals(compareNumber)
                && this.contains(compareNumber);
    }

    @Override
    public String toString() {
        return "Baseball" + baseballNumbers;
    }
}
