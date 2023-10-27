package baseball.io;

import camp.nextstep.edu.missionutils.Console;

public class InputReader {

    private static InputReader INSTANCE;

    private InputReader() {
    }

    public static InputReader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputReader();
        }
        return INSTANCE;
    }

    public String readLine() {
        return Console.readLine();
    }
}
