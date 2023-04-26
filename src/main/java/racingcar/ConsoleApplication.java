package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.game.ConsoleGameDao;
import racingcar.dao.participates.ConsoleParticipatesDao;
import racingcar.dao.player.ConsolePlayerDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        final RacingCarService racingCarService = new RacingCarService(new ConsoleGameDao(), new ConsolePlayerDao(),
                new ConsoleParticipatesDao());
        final ConsoleController consoleController = new ConsoleController(racingCarService, new InputView(),
                new OutputView());
        consoleController.run();
    }
}
