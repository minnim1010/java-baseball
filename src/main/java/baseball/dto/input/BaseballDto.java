package baseball.dto.input;

import baseball.common.exception.ErrorMessage;
import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import baseball.validator.InputValidator;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public record BaseballDto(String baseball) {

    private static final Pattern BASEBALL_PATTERN = Pattern.compile("^[1-9]+$");

    public BaseballDto {
        InputValidator.validateNonBlank(baseball);
        InputValidator.validateSize(baseball.length());
        validateFormat(baseball);
    }

    private static void validateFormat(String baseball) {
        if (!BASEBALL_PATTERN.matcher(baseball).matches()) {
            throw ErrorMessage.INVALID_RANGE_VALUE.getException();
        }
    }

    public Baseball toBaseball() {
        String[] splits = baseball.split("");

        List<BaseballNumber> baseballNumbers = Arrays.stream(splits)
                .map(Integer::parseInt)
                .map(BaseballNumber::new)
                .toList();

        return Baseball.from(baseballNumbers);
    }
}
