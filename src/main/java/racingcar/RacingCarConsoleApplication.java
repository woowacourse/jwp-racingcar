package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.dao.CarConsoleDao;
import racingcar.dao.GameConsoleDao;
import racingcar.service.RacingCarService;
import racingcar.strategy.RandomMovingStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(
                new InputView(),
                new OutputView(),
                new RacingCarService(
                        new RandomMovingStrategy(), new GameConsoleDao(), new CarConsoleDao()
                )
        );

        racingCarConsoleController.execute();
    }
}
