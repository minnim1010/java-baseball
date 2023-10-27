package baseball.validator;

import baseball.common.exception.ErrorMessage;
import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseballValidator {

    //인스턴스화 방지
    private BaseballValidator() {
        throw new AssertionError();
    }

    public static void validateLength(List<BaseballNumber> baseballNumbers) {
        if (baseballNumbers.size() != Baseball.LENGTH) {
            throw ErrorMessage.INVALID_LENGTH.getException();
        }
    }

    public static void validateUnique(List<BaseballNumber> baseballNumbers) {
        Set<BaseballNumber> uniqueNumbers = new HashSet<>(baseballNumbers);
        if (uniqueNumbers.size() != Baseball.LENGTH) {
            throw ErrorMessage.DUPLICATED.getException();
        }
    }

    public static void validateWithinBounds(int number) {
        if (!isWithinBounds(number)) {
            throw ErrorMessage.INVALID_RANGE_VALUE.getException();
        }
    }

    private static boolean isWithinBounds(int number) {
        return BaseballNumber.MIN_VALUE <= number && number <= BaseballNumber.MAX_VALUE;
    }
}
