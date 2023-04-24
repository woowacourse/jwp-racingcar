package racingcar;

import racingcar.controller.ConsoleRacingGameController;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final ConsoleRacingGameController controller = new ConsoleRacingGameController();
        controller.play();
    }
}
