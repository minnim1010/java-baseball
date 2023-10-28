package baseball.io.writer;

public class CommandLineWriter implements Writer {

    private static CommandLineWriter INSTANCE;

    private CommandLineWriter() {
    }

    public static CommandLineWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommandLineWriter();
        }
        return INSTANCE;
    }

    public void write(String message) {
        System.out.print(message);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }
}
