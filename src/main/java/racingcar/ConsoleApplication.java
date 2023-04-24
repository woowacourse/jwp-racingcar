package racingcar;

import racingcar.consolecontroller.ConsoleController;
import racingcar.domain.DummyRacingGameRepository;
import racingcar.domain.numbergenerator.RandomNumberGenerator;
import racingcar.service.RacingGameServiceImpl;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public final class ConsoleApplication {

    public static void main(String[] args) {
        final var controller = new ConsoleController(
                new RacingGameServiceImpl(
                        new RandomNumberGenerator(),
                        new DummyRacingGameRepository()
                ),
                new InputView(),
                new OutputView()
        );
        try {
            controller.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
