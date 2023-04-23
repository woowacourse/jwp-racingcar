package racingcar;

import racingcar.controller.RacingCarConsoleController;
import racingcar.service.RacingCarService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        try {
            RacingCarConsoleController racingCarConsoleController = new RacingCarConsoleController(
                    new InputView(System.in), new OutputView(), new RacingCarService()
            );
            racingCarConsoleController.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
