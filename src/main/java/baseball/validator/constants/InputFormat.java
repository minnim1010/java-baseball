package baseball.validator.constants;

import java.util.regex.Pattern;

public enum InputFormat {

    BASEBALL(Pattern.compile("^[1-9]{3}$"));

    private final Pattern pattern;

    InputFormat(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }
}