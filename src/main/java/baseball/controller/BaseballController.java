package baseball.controller;

import baseball.dto.BaseballDto;
import baseball.dto.ReplayChoiceDto;
import baseball.model.Baseball;
import baseball.model.BaseballGameResult;
import baseball.model.GameStatus;
import baseball.service.BaseballService;
import baseball.view.BaseballView;
import camp.nextstep.edu.missionutils.Console;

public class BaseballController {

    private final BaseballView baseballView;
    private final BaseballService baseballService;

    public BaseballController(BaseballView baseballView, BaseballService baseballService) {
        this.baseballView = baseballView;
        this.baseballService = baseballService;
    }

    public void run() {
        baseballView.startGame();

        boolean play = true;
        while (play) {
            playGame();
            play = askReplayChoice();
        }

        releaseResource();
    }

    private void playGame() {
        Baseball answer = baseballService.createAnswerBaseball();

        boolean isClear = false;
        while (!isClear) {
            Baseball guess = getGuessBaseball();
            BaseballGameResult result = baseballService.calculateResult(answer, guess);
            showGameResult(result);
            isClear = result.isClear();
        }

        baseballView.clearGame();
    }

    private Baseball getGuessBaseball() {
        BaseballDto baseballDto = baseballView.inputNumber();
        return baseballDto.toBaseball();
    }

    private void showGameResult(BaseballGameResult result) {
        baseballView.showGameResult(result.getResultMessage());
    }

    private boolean askReplayChoice() {
        ReplayChoiceDto replayChoiceDto = baseballView.replayGame();
        GameStatus gameStatus = GameStatus.from(replayChoiceDto.replayChoice());

        return gameStatus.equals(GameStatus.REPLAY);
    }

    private void releaseResource() {
        Console.close();
    }
}
