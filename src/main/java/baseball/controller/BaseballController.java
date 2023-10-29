package baseball.controller;

import baseball.common.constants.GameStatus;
import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.model.Baseball;
import baseball.view.BaseballView;

public class BaseballController {

    private final BaseballView baseballView;

    public BaseballController(BaseballView baseballView) {
        this.baseballView = baseballView;
    }

    public void run() {
        baseballView.startGame();

        boolean isPlaying = true;
        while (isPlaying) {
            playGame();
            isPlaying = askForReplay();
        }
    }

    private void playGame() {
        Baseball answer = BaseballService.createAnswerBaseball();

        boolean isClear = false;
        while (!isClear) {
            BaseballDto guess = baseballView.inputNumber();
            GameResultDto result = BaseballService.calculateResult(answer, guess);
            baseballView.showGameResult(result);
            isClear = isClear(result);
        }

        baseballView.clearGame();
    }

    private boolean isClear(GameResultDto result) {
        return result.strikeCount() == Baseball.LENGTH;
    }

    private boolean askForReplay() {
        ReplayChoiceDto replayChoiceDto = baseballView.replayGame();

        return GameStatus.isReplay(replayChoiceDto.replayChoice());
    }
}
