package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.car.CacheCarDao;
import racingcar.dao.game.CacheGameDao;
import racingcar.service.GameService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        final ConsoleController controller = new ConsoleController(
                new InputView(),
                new OutputView(),
                new GameService(new RandomNumberGenerator(), new CacheCarDao(), new CacheGameDao())
        );

        controller.run();
    }
}
