package baseball.io.reader;

import camp.nextstep.edu.missionutils.Console;

public class CommandLineReader implements Reader {

    private static CommandLineReader INSTANCE;

    private CommandLineReader() {
    }

    public static CommandLineReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandLineReader();
        }
        return INSTANCE;
    }

    public String readLine() {
        return Console.readLine();
    }
}
