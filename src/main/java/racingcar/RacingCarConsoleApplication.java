package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.car.InMemoryCarDao;
import racingcar.dao.game.InMemoryGameDao;
import racingcar.service.GameService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final ConsoleController controller = new ConsoleController(
                new InputView(),
                new OutputView(),
                new GameService(new RandomNumberGenerator(), new InMemoryCarDao(), new InMemoryGameDao())
        );

        controller.run();
    }
}
