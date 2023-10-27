package baseball.config;

import baseball.controller.BaseballController;
import baseball.io.InputReader;
import baseball.io.OutputWriter;
import baseball.service.BaseballService;
import baseball.view.BaseballView;

public class BaseballConfig {

    protected BaseballConfig() {
    }

    public static BaseballController getBaseballController() {
        InputReader inputReader = getInputReader();
        OutputWriter outputWriter = getOutputWriter();
        BaseballView baseballView = getBaseballView(inputReader, outputWriter);
        BaseballService baseballService = getBaseballService();

        return new BaseballController(baseballView, baseballService);
    }

    private static InputReader getInputReader() {
        return InputReader.getInstance();
    }

    private static OutputWriter getOutputWriter() {
        return OutputWriter.getInstance();
    }

    private static BaseballService getBaseballService() {
        return BaseballService.getInstance();
    }

    private static BaseballView getBaseballView(InputReader reader, OutputWriter writer) {
        return BaseballView.getInstance(reader, writer);
    }
}
