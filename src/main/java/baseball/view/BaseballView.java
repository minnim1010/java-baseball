package baseball.view;

import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.io.InputReader;
import baseball.io.OutputWriter;
import baseball.view.constants.GameResultMessage;
import baseball.view.constants.Message;

public class BaseballView {

    private static BaseballView INSTANCE;

    private final InputReader reader;
    private final OutputWriter writer;

    private BaseballView(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static BaseballView getInstance(InputReader reader, OutputWriter writer) {
        if (INSTANCE == null) {
            INSTANCE = new BaseballView(reader, writer);
        }
        return INSTANCE;
    }

    public static BaseballView getInstance() {
        return INSTANCE;
    }

    public BaseballDto inputNumber() {
        writer.write(Message.INPUT_NUMBER);

        String input = reader.readLine();
        return new BaseballDto(input);
    }

    public ReplayChoiceDto replayGame() {
        writer.writeLine(Message.INPUT_REPLAY);

        String input = reader.readLine();
        return new ReplayChoiceDto(input);
    }

    public void startGame() {
        writer.writeLine(Message.START_GAME);
    }

    public void showGameResult(GameResultDto gameResultDto) {
        String gameResult = GameResultMessage.getMessage(gameResultDto.strikeCount(), gameResultDto.ballCount());
        writer.writeLine(gameResult);
    }

    public void clearGame() {
        writer.writeLine(Message.GAME_CLEAR);
    }
}
