package baseball.validator;

import baseball.validator.constants.ValidInputPattern;
import java.util.regex.Pattern;

public class RegexValidator {

    //인스턴스화 방지
    private RegexValidator() {
        throw new AssertionError();
    }

    public static void validate(ValidInputPattern validInputPattern, String string) {
        Pattern pattern = validInputPattern.getPattern();

        if (!pattern.matcher(string).matches()) {
            throw new IllegalArgumentException(validInputPattern.getErrorMessage());
        }
    }
}