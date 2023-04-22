package racingcar;

import racingcar.console.ConsoleRacingGameController;
import racingcar.domain.CarRandomNumberGenerator;
import racingcar.repository.console.ConsoleCarRepository;
import racingcar.repository.console.ConsoleGameRepository;
import racingcar.repository.console.ConsoleWinnerRepository;
import racingcar.service.RacingGameService;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleRacingGameController controller = new ConsoleRacingGameController(
                new RacingGameService(
                        new ConsoleCarRepository(),
                        new ConsoleGameRepository(),
                        new ConsoleWinnerRepository(),
                        new CarRandomNumberGenerator()
                ));
        controller.run();
    }
}
