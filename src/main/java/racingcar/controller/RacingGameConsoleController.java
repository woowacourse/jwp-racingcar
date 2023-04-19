package racingcar.controller;

import racingcar.dao.PlayerConsoleDao;
import racingcar.dao.RacingGameConsoleDao;
import racingcar.domain.RacingCars;
import racingcar.service.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    public void play() {
        final RacingGameService racingGameService = new RacingGameService(new RacingGameConsoleDao(), new PlayerConsoleDao());

        final String names = InputView.requestCarName();
        final int tryCount = InputView.requestTryCount();

        final RacingCars racingCars = racingGameService.run(names, tryCount);

        OutputView.printResult(racingCars);
    }

}
