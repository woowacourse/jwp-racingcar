package racingcar;

import java.util.Scanner;
import racingcar.controller.RaceController;
import racingcar.dao.GameInMemoryDao;
import racingcar.dao.PlayerInMemoryDao;
import racingcar.domain.RandomNumberGenerator;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RaceController raceController = new RaceController(
                new InputView(scanner),
                new OutputView(),
                new RacingCarService(new GameInMemoryDao(), new PlayerInMemoryDao(), new RandomNumberGenerator())
        );
        raceController.play();
        scanner.close();
    }
}
