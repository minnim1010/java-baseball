package baseball.io;

public class OutputWriter {

    private static OutputWriter INSTANCE;

    private OutputWriter() {
    }

    public static OutputWriter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OutputWriter();
        }
        return INSTANCE;
    }

    public void write(final String message) {
        System.out.print(message);
    }

    public void writeLine(final String message) {
        System.out.println(message);
    }
}
