package racing.console;

import racing.console.controller.RacingCarConsoleController;
import racing.console.ui.input.InputView;
import racing.console.ui.output.OutputView;

public class RacingCarConsoleApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RacingCarConsoleController racingCarConsoleController
                = new RacingCarConsoleController(inputView, outputView);

        racingCarConsoleController.playGame();
    }
}
