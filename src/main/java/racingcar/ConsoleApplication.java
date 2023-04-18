package racingcar;

import racingcar.controller.ConsoleRacingGameController;
import racingcar.domain.CarRandomNumberGenerator;
import racingcar.mapper.ConsoleCarResultMapper;
import racingcar.mapper.ConsolePlayerResultMapper;
import racingcar.service.RacingGameService;

public class ConsoleApplication {
    public static void main(String[] args) {
        ConsoleRacingGameController controller = new ConsoleRacingGameController(
                new RacingGameService(
                        new ConsoleCarResultMapper(),
                        new ConsolePlayerResultMapper(),
                        new CarRandomNumberGenerator()
                ));
        controller.run();
    }
}
