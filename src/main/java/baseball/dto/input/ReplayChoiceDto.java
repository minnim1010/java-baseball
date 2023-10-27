package baseball.dto.input;

import baseball.validator.InputValidator;

public record ReplayChoiceDto(String replayChoice) {

    public ReplayChoiceDto {
        InputValidator.validateNonBlank(replayChoice);
        InputValidator.validateReplayChoice(replayChoice);
    }
}
