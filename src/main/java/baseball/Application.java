package baseball;

import baseball.common.config.BaseballConfig;
import baseball.controller.BaseballController;
import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        final BaseballController baseballController = BaseballConfig.getBaseballController();
        try {
            baseballController.run();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        } finally {
            Console.close();
        }
    }
}
