package baseball.constants;

public enum Message {

    START_GAME("숫자 야구 게임을 시작합니다."),
    INPUT_NUMBER("숫자를 입력해주세요 : "),
    GAME_CLEAR("3개의 숫자를 모두 맞히셨습니다! 게임 종료"),
    INPUT_REPLAY("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."),
    NOTHING("낫싱"),
    BALL("볼"),
    STRIKE("스트라이크");

    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
