package baseball.common.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum GameStatus {
    REPLAY("1"),
    EXIT("2");

    private final String code;
    private static final Map<String, GameStatus> statusByCode =
            Stream.of(values()).collect(Collectors.toMap(GameStatus::getCode, v -> v));

    GameStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static boolean contains(String code) {
        return statusByCode.containsKey(code);
    }

    public static boolean isReplay(String code) {
        return code.equals(REPLAY.code);
    }
}
