package racingcar;

import racingcar.controller.ConsoleRacingGameManager;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleRacingGameManager manager = new ConsoleRacingGameManager();

        manager.run();
    }
}
