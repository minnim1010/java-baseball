package baseball.io.reader;

import camp.nextstep.edu.missionutils.Console;

public class CommandLineReader implements Reader {

    public String readLine() {
        return Console.readLine();
    }
}
