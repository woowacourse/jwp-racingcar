package racingcar;

import racingcar.controller.ConsoleRacingCarController;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarApplication {

    private static final NumberGenerator NUMBER_GENERATOR = new RandomNumberGenerator();
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    public static void main(String[] args) {
        ConsoleRacingCarController controller = new ConsoleRacingCarController(
                NUMBER_GENERATOR,
                INPUT_VIEW,
                OUTPUT_VIEW);
        controller.run();
    }
}
