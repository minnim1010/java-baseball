package baseball.validator;

import baseball.common.exception.ErrorMessage;
import baseball.model.Baseball;

public class InputValidator {

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
}
