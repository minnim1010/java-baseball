package baseball.view;

import baseball.common.constants.GameResultMessage;
import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.io.reader.Reader;
import baseball.io.writer.Writer;

public class BaseballView {

    private final Reader reader;
    private final Writer writer;

    public static final String START_GAME = "숫자 야구 게임을 시작합니다.";
    public static final String INPUT_NUMBER = "숫자를 입력해주세요 : ";
    public static final String GAME_CLEAR = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    public static final String INPUT_REPLAY = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public BaseballView(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void startGame() {
        writer.writeLine(START_GAME);
    }

    public BaseballDto inputNumber() {
        writer.write(INPUT_NUMBER);
        return new BaseballDto(reader.readLine());
    }

    public void showGameResult(GameResultDto gameResultDto) {
        String gameResult = GameResultMessage.getMessage(gameResultDto.strikeCount(), gameResultDto.ballCount());
        writer.writeLine(gameResult);
    }

    public void clearGame() {
        writer.writeLine(GAME_CLEAR);
    }

    public ReplayChoiceDto replayGame() {
        writer.writeLine(INPUT_REPLAY);
        return new ReplayChoiceDto(reader.readLine());
    }
}
