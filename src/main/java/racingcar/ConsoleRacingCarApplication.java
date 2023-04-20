package racingcar;

import java.util.HashMap;
import racingcar.controller.ConsoleRaceController;
import racingcar.domain.RaceNumberGenerator;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.ConsoleCarDao;
import racingcar.domain.dao.ConsoleRaceResultDao;
import racingcar.domain.dao.RaceResultDao;
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
        final RaceResultDao raceResultDao = new ConsoleRaceResultDao(new HashMap<>());
        final CarDao carDao = new ConsoleCarDao(new HashMap<>());
        return new RaceResultEntityRepositoryImpl(raceResultDao, carDao);
    }
}
