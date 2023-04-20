package racingcar;

import racingcar.controller.ConsoleRaceController;
import racingcar.domain.RaceNumberGenerator;
import racingcar.domain.dao.ConsoleCarDao;
import racingcar.domain.dao.ConsoleRaceResultDao;
import racingcar.domain.repository.RaceResultEntityRepositoryImpl;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        final ConsoleRaceController consoleRaceController = makeConsoleRaceController();
        consoleRaceController.play();
    }

    private static ConsoleRaceController makeConsoleRaceController() {
        return new ConsoleRaceController(makeRaceService(), new InputView(), new OutputView());
    }

    private static RaceService makeRaceService() {
        return new RaceService(new RaceNumberGenerator(), makeRaceRepository());
    }

    private static RaceResultEntityRepositoryImpl makeRaceRepository() {
        return new RaceResultEntityRepositoryImpl(new ConsoleRaceResultDao(), new ConsoleCarDao());
    }
}
