package baseball.controller;

import baseball.common.constants.GameStatus;
import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.model.Baseball;
import baseball.service.BaseballService;
import baseball.view.BaseballView;

public class BaseballController {

    private final BaseballView baseballView;
    private final BaseballService baseballService;

    public BaseballController(BaseballView baseballView, BaseballService baseballService) {
        this.baseballView = baseballView;
        this.baseballService = baseballService;
    }

    public void run() {
        baseballView.startGame();

        boolean isPlaying = true;
        while (isPlaying) {
            playGame();
            isPlaying = askForReplay();
        }
    }

    private static boolean isClear(GameResultDto result) {
        return result.strikeCount() == Baseball.LENGTH;
    }

    private void playGame() {
        Baseball answer = baseballService.createAnswerBaseball();

        boolean isClear = false;
        while (!isClear) {
            BaseballDto guess = baseballView.inputNumber();
            GameResultDto result = baseballService.calculateResult(answer, guess);
            baseballView.showGameResult(result);
            isClear = isClear(result);
        }

        baseballView.clearGame();
    }

    private boolean askForReplay() {
        ReplayChoiceDto replayChoiceDto = baseballView.replayGame();

        return GameStatus.isReplay(replayChoiceDto.replayChoice());
    }
}
