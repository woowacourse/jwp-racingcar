package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.GameConsoleDao;
import racingcar.dao.ParticipantConsoleDao;
import racingcar.dao.PlayerConsoleDao;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        RacingCarService racingCarService = getRacingCarService();
        ConsoleController consoleController = getConsoleController(racingCarService);
        consoleController.plays();
    }

    private static ConsoleController getConsoleController(final RacingCarService racingCarService) {
        return new ConsoleController(
                new InputView(),
                new OutputView(),
                racingCarService);
    }

    private static RacingCarService getRacingCarService() {
        return new RacingCarService(
                new GameConsoleDao(),
                new PlayerConsoleDao(),
                new ParticipantConsoleDao());
    }

}
