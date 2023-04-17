package racingcar;

import racingcar.consolecontroller.ConsoleController;
import racingcar.domain.ConsoleRacingGameRepository;
import racingcar.domain.numbergenerator.RandomNumberGenerator;
import racingcar.service.RacingGameServiceImpl;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class ConsoleApplication {

    public static void main(String[] args) {
        final var controller = new ConsoleController(
                new RacingGameServiceImpl(
                        new RandomNumberGenerator(),
                        new ConsoleRacingGameRepository()
                ),
                new InputView(),
                new OutputView()
        );

        controller.run();
    }
}
