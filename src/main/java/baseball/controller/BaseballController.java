package baseball.controller;

import baseball.dto.input.BaseballDto;
import baseball.dto.input.ReplayChoiceDto;
import baseball.dto.output.GameResultDto;
import baseball.model.Baseball;
import baseball.model.BaseballGameResultType;
import baseball.model.GameResult;
import baseball.model.GameStatus;
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

    private void playGame() {
        Baseball answer = baseballService.createAnswerBaseball();

        boolean isClear = false;
        while (!isClear) {
            Baseball guess = getGuessBaseball();
            GameResult result = baseballService.calculateResult(answer, guess);
            showGameResult(result);
            isClear = result.isClear();
        }

        baseballView.clearGame();
    }

    private Baseball getGuessBaseball() {
        BaseballDto baseballDto = baseballView.inputNumber();
        return baseballDto.toBaseball();
    }

    private void showGameResult(GameResult result) {
        int strikeCount = result.getCount(BaseballGameResultType.STRIKE);
        int ballCount = result.getCount(BaseballGameResultType.BALL);
        GameResultDto gameResultDto = new GameResultDto(strikeCount, ballCount);

        baseballView.showGameResult(gameResultDto);
    }

    private boolean askForReplay() {
        ReplayChoiceDto replayChoiceDto = baseballView.replayGame();

        return GameStatus.isReplay(replayChoiceDto.replayChoice());
    }
}
