package racingcar;

import racingcar.controller.RacingController;
import racingcar.repository.InMemoryRacingGameRepository;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        RacingController racingController = new RacingController(
                new InputView(),
                new OutputView(),
                new RacingCarService(new RandomNumberGenerator(), new InMemoryRacingGameRepository())
        );

        racingController.play();
    }
}
