package racingcar;

import java.io.IOException;

import racingcar.controller.RacingGameConsoleController;
import racingcar.dao.PlayerInMemoryDao;
import racingcar.dao.RacingGameInMemoryDao;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) throws IOException {
        final RacingGameConsoleController racingGameConsoleController = new RacingGameConsoleController(
                new InputView(),
                new OutputView(),
                new RacingGameService(
                        new RacingGameInMemoryDao(),
                        new PlayerInMemoryDao()
                )
        );
        racingGameConsoleController.run();
    }
}
