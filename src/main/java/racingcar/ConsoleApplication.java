package racingcar;

import racingcar.controller.RacingGameManager;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGameManager manager = new RacingGameManager();

        manager.run();
    }
}
