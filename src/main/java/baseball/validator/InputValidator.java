package baseball.validator;

import baseball.common.constants.GameStatus;
import baseball.common.exception.ErrorMessage;
import baseball.model.Baseball;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern BASEBALL_PATTERN = Pattern.compile("^[1-9]+$");

    //인스턴스화 방지
    private InputValidator() {
        throw new AssertionError();
    }

    public static void validateNonBlank(String message) {
        if (isBlank(message)) {
            throw ErrorMessage.BLANK_INPUT.getException();
        }
    }

    private static boolean isBlank(String message) {
        return message == null || message.isBlank();
    }

    public static void validateSize(int length) {
        if (length != Baseball.LENGTH) {
            throw ErrorMessage.INVALID_LENGTH.getException();
        }
    }

    public static void validateBaseballFormat(String baseball) {
        if (!BASEBALL_PATTERN.matcher(baseball).matches()) {
            throw ErrorMessage.INVALID_RANGE_VALUE.getException();
        }
    }

    public static void validateReplayChoice(String replayChoice) {
        if (!GameStatus.contains(replayChoice)) {
            throw ErrorMessage.INVALID_REPLAY_INPUT.getException();
        }
    }
}
