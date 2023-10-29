package baseball.common.config;

import baseball.controller.BaseballController;
import baseball.io.reader.CommandLineReader;
import baseball.io.reader.Reader;
import baseball.io.writer.CommandLineWriter;
import baseball.io.writer.Writer;
import baseball.view.BaseballView;

public class BaseballConfig {

    protected BaseballConfig() {
    }

    public static BaseballController getBaseballController() {
        Reader reader = getReader();
        Writer writer = getWriter();
        return new BaseballController(getBaseballView(reader, writer));
    }

    private static Reader getReader() {
        return new CommandLineReader();
    }

    private static Writer getWriter() {
        return new CommandLineWriter();
    }

    private static BaseballView getBaseballView(Reader reader, Writer writer) {
        return new BaseballView(reader, writer);
    }
}
