package baseball.config;

import baseball.controller.BaseballController;
import baseball.io.InputReader;
import baseball.io.OutputWriter;
import baseball.service.BaseballService;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseballConfig {
    private static final InputReader inputReader = InputReader.getInstance();
    private static final OutputWriter outputWriter = OutputWriter.getInstance();
    private static final BaseballService baseballService = BaseballService.getInstance();

    protected BaseballConfig() {
    }

    public static final BaseballController getBaseballController() {
        InputView inputView = new InputView(inputReader, outputWriter);
        OutputView outputView = new OutputView(outputWriter);

        return new BaseballController(inputView, outputView, baseballService);
    }
}
