package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.dao.MemoryRacingCarDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        new RacingCarConsoleController(new InputView(),
                new OutputView(),
                new RacingCarService(new MemoryRacingCarDao()))
                .run();
    }
}
