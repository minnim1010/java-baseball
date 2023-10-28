package baseball.view;

import baseball.common.constants.GameResultMessage;
import baseball.common.constants.Message;
import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.io.reader.Reader;
import baseball.io.writer.Writer;

public class BaseballView {

    private static BaseballView INSTANCE;

    private final Reader reader;
    private final Writer writer;

    private BaseballView(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static BaseballView getInstance(Reader reader, Writer writer) {
        if (INSTANCE == null) {
            INSTANCE = new BaseballView(reader, writer);
        }
        return INSTANCE;
    }

    public void startGame() {
        writer.writeLine(Message.START_GAME);
    }

    public BaseballDto inputNumber() {
        writer.write(Message.INPUT_NUMBER);
        return new BaseballDto(reader.readLine());
    }

    public void showGameResult(GameResultDto gameResultDto) {
        String gameResult = GameResultMessage.getMessage(gameResultDto.strikeCount(), gameResultDto.ballCount());
        writer.writeLine(gameResult);
    }

    public void clearGame() {
        writer.writeLine(Message.GAME_CLEAR);
    }

    public ReplayChoiceDto replayGame() {
        writer.writeLine(Message.INPUT_REPLAY);
        return new ReplayChoiceDto(reader.readLine());
    }
}
