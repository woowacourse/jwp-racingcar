package racingcar;

import java.util.Scanner;
import racingcar.controller.ConsoleRacingCarController;
import racingcar.dao.LocalCarDao;
import racingcar.dao.LocalRacingGameDao;
import racingcar.domain.RandomNumberGenerator;
import racingcar.repository.DaoRacingCarRepository;
import racingcar.repository.RacingCarRepository;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    public static void main(String[] args) {
        ConsoleRacingCarController controller = new ConsoleRacingCarController(initRacingCarService(),
                new InputView(new Scanner(System.in)),
                new OutputView());
        controller.run();
    }

    private static RacingCarService initRacingCarService() {
        return new RacingCarService(initRacingCarRepository(), new RandomNumberGenerator());
    }

    private static RacingCarRepository initRacingCarRepository() {
        return new DaoRacingCarRepository(new LocalRacingGameDao(), new LocalCarDao());
    }
}
