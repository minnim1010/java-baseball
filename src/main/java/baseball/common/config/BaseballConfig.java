package baseball.common.config;

import baseball.controller.BaseballController;
import baseball.io.reader.CommandLineReader;
import baseball.io.reader.Reader;
import baseball.io.writer.CommandLineWriter;
import baseball.io.writer.Writer;
import baseball.service.BaseballService;
import baseball.view.BaseballView;

public class BaseballConfig {

    protected BaseballConfig() {
    }

    public static BaseballController getBaseballController() {
        Reader reader = getCommandLineReader();
        Writer writer = getCommandLineWriter();
        BaseballView baseballView = getBaseballView(reader, writer);

        BaseballService baseballService = getBaseballService();

        return new BaseballController(baseballView, baseballService);
    }

    private static CommandLineReader getCommandLineReader() {
        return CommandLineReader.getInstance();
    }

    private static CommandLineWriter getCommandLineWriter() {
        return CommandLineWriter.getInstance();
    }

    private static BaseballService getBaseballService() {
        return BaseballService.getInstance();
    }

    private static BaseballView getBaseballView(Reader reader, Writer writer) {
        return BaseballView.getInstance(reader, writer);
    }
}
