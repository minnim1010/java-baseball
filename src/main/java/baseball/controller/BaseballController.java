package baseball.controller;

import baseball.constants.GameStatus;
import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayDto;
import baseball.model.Baseball;
import baseball.model.BaseballGameResult;
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
        ReplayDto replayDto = baseballView.replayGame();
        GameStatus gameStatus = GameStatus.from(replayDto.replay());

        return gameStatus.equals(GameStatus.REPLAY);
    }

    private void releaseResource() {
        Console.close();
    }
}
