package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.ConsoleGameDao;
import racingcar.dao.ConsoleParticipatesDao;
import racingcar.dao.ConsolePlayerDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        ConsoleController consoleController = new ConsoleController(
                new RacingCarService(new ConsoleGameDao(), new ConsolePlayerDao(), new ConsoleParticipatesDao()),
                new InputView(),
                new OutputView()
        );
        consoleController.run();
    }
}
