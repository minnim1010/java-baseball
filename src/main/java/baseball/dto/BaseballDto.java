package baseball.dto;

import baseball.common.exception.ErrorMessage;
import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import baseball.validator.InputValidator;
import baseball.validator.constants.InputFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public record BaseballDto(String baseball) {

    public BaseballDto {
        InputValidator.validate(baseball);
        validateFormat(baseball);
    }

    private static void validateFormat(String baseball) {
        Pattern pattern = InputFormat.BASEBALL.getPattern();

        if (!pattern.matcher(baseball).matches()) {
            throw ErrorMessage.INVALID_BASEBALL_INPUT.getException();
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
