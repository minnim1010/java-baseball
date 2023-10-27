package baseball.dto.input;

import baseball.common.exception.ErrorMessage;
import baseball.model.GameStatus;
import baseball.validator.InputValidator;

public record ReplayChoiceDto(String replayChoice) {

    public ReplayChoiceDto {
        InputValidator.validateNonBlank(replayChoice);
        validate(replayChoice);
    }

    private static void validate(String replayChoice) {
        if (!GameStatus.contains(replayChoice)) {
            throw ErrorMessage.INVALID_REPLAY_INPUT.getException();
        }
    }
}
