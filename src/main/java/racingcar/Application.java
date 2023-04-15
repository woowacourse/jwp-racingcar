package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.utils.ScannerInputReader;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(new ScannerInputReader());

        ConsoleController service = new ConsoleController(outputView, inputView);
        service.run();
    }
}
