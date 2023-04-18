package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.repository.EmptyRacingGameRepository;
import racingcar.service.RacingCarService;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final RacingCarService racingCarService = new RacingCarService(new EmptyRacingGameRepository());
        new ConsoleRacingCarController(racingCarService).run();
    }
}
