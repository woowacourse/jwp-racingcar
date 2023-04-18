package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.dao.InMemoryRacingGameRepository;
import racingcar.services.RacingGameService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleApplication {
    public static void main(String[] args) {
        RacingGameService racingGameService = new RacingGameService(InMemoryRacingGameRepository.create());
        ConsoleController mainController = new ConsoleController(InputView.getInstance(), OutputView.getInstance(), racingGameService);
        mainController.play();
    }
}
