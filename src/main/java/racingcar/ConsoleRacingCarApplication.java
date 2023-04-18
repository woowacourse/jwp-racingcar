package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.service.RacingCarService;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        new ConsoleRacingCarController(new RacingCarService()).run();
    }
}
