package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.dao.InMemoryGameDao;
import racingcar.dao.InMemoryRacingCarDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {
    public static void main(String[] args) {
        RacingCarConsoleController racingCarConsoleController =
                new RacingCarConsoleController(
                        new InputView(),
                        new OutputView(),
                        new RacingCarService(
                                new InMemoryGameDao(),
                                new InMemoryRacingCarDao()
                        )
                );
        racingCarConsoleController.run();
    }
}
