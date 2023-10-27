package baseball.common.constants;

public enum GameResultMessage {
    NOTHING("낫싱"),
    ONLY_STRIKE("%d스트라이크"),
    ONLY_BALL("%d볼"),
    STRIKE_AND_BALL("%d볼 %d스트라이크");

    private final String message;

    GameResultMessage(String message) {
        this.message = message;
    }

    public static String getMessage(int strikeCount, int ballCount) {
        if (strikeCount == 0 && ballCount == 0) {
            return NOTHING.message;
        }
        if (strikeCount == 0) {
            return String.format(ONLY_BALL.message, ballCount);
        }
        if (ballCount == 0) {
            return String.format(ONLY_STRIKE.message, strikeCount);
        }
        return String.format(STRIKE_AND_BALL.message, strikeCount, ballCount);
    }
}
