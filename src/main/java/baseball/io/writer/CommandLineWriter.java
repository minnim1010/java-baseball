package baseball.io.writer;

public class CommandLineWriter implements Writer {

    public void write(String message) {
        System.out.print(message);
    }

    public void writeLine(String message) {
        System.out.println(message);
    }
}
