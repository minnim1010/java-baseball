package baseball.common.exception;

public enum ErrorMessage {

    BLANK_INPUT("공백이 아닌 값을 입력해주세요."),
    INVALID_RANGE_VALUE("각 문자는 1 이상 9 이하 값을 가져야 합니다."),
    INVALID_LENGTH("길이가 3이어야 합니다."),
    DUPLICATED("각 숫자가 중복되지 않아야 합니다."),
    INVALID_REPLAY_INPUT("1 또는 2를 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public BaseballException getException() {
        return new BaseballException(this.message);
    }
}
