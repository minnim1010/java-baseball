package baseball.model;

import baseball.validator.BaseballValidator;
import java.util.Collection;
import java.util.List;

public class Baseball {

    public static final int LENGTH = 3;

    private final List<BaseballNumber> baseballNumbers;

    private Baseball(Collection<Integer> baseballNumbers) {
        this.baseballNumbers = baseballNumbers.stream()
                .map(BaseballNumber::new)
                .toList();
    }

    public static Baseball from(Collection<Integer> baseballNumbers) {
        BaseballValidator.validateLength(baseballNumbers);
        BaseballValidator.validateUnique(baseballNumbers);

        return new Baseball(baseballNumbers);
    }

    public List<BaseballNumber> getBaseballNumbers() {
        return baseballNumbers;
    }

    public BaseballGameResult match(Baseball compare) {
        BaseballGameResult result = new BaseballGameResult();

        updateResult(result, compare);
        return result;
    }

    private void updateResult(BaseballGameResult result, Baseball compare) {
        List<BaseballNumber> compareNumbers = compare.getBaseballNumbers();

        for (int i = 0; i < Baseball.LENGTH; i++) {
            BaseballNumber number = baseballNumbers.get(i);
            BaseballNumber compareNumber = compareNumbers.get(i);

            if (isStrike(number, compareNumber)) {
                result.add(BaseballGameResultType.STRIKE);
            } else if (isBall(compareNumber)) {
                result.add(BaseballGameResultType.BALL);
            }
        }
    }

    private boolean isStrike(BaseballNumber number, BaseballNumber compareNumber) {
        return number.equals(compareNumber);
    }

    private boolean isBall(BaseballNumber compareNumber) {
        return baseballNumbers.contains(compareNumber);
    }

    @Override
    public String toString() {
        return "Baseball" + baseballNumbers;
    }
}
