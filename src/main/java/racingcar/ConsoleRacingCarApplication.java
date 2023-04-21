package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.domain.RandomNumberPicker;
import racingcar.repository.EmptyRacingGameRepository;
import racingcar.service.RacingCarService;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        new ConsoleRacingCarController(generateService()).run();
    }

    private static RacingCarService generateService() {
        return new RacingCarService(new EmptyRacingGameRepository(),
                RandomNumberPicker.getInstance());
    }
}
