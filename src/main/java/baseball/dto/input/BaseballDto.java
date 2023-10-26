package baseball.dto.input;

import baseball.model.Baseball;
import baseball.model.BaseballNumber;
import baseball.validator.InputValidator;
import baseball.validator.RegexValidator;
import baseball.validator.ValidInputPattern;
import java.util.Arrays;
import java.util.List;

public record BaseballDto(String baseball) {

    public BaseballDto {
        InputValidator.validate(baseball);
        RegexValidator.validate(ValidInputPattern.BASEBALL, baseball);
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
