package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.domain.CarRandomNumberGenerator;
import racingcar.mapper.console.ConsoleCarMapper;
import racingcar.mapper.console.ConsoleGameMapper;
import racingcar.mapper.console.ConsoleWinnerMapper;
import racingcar.service.RacingGameService;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleRacingGameController controller = new ConsoleRacingGameController(
                new RacingGameService(
                        new ConsoleCarMapper(),
                        new ConsoleGameMapper(),
                        new ConsoleWinnerMapper(),
                        new CarRandomNumberGenerator()
                ));
        controller.run();
    }
}
