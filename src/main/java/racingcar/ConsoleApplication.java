package racingcar;

import racingcar.controller.RaceConsoleController;
import racingcar.domain.RaceNumberGenerator;

public class ConsoleApplication {
    public static void main(String[] args) {
        final RaceConsoleController raceConsoleController = new RaceConsoleController(new RaceNumberGenerator());
        raceConsoleController.race();
    }
}
