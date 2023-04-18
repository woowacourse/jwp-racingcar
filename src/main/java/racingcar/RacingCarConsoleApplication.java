package racingcar;

import racingcar.controller.RacingCarConsoleController;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        try {
            new RacingCarConsoleController().run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
