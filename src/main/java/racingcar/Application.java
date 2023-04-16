package racingcar;

import racingcar.controller.RacingController;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        try {
            final RacingController racingGame = new RacingController(
                    new InputView(),
                    new OutputView()
            );

            racingGame.race();

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
