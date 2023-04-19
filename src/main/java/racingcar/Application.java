package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.service.ConsoleService;
import racingcar.utils.ScannerInputReader;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(new ScannerInputReader());
        ConsoleService service = new ConsoleService();

        ConsoleController controller = new ConsoleController(outputView, inputView, service);
        controller.run();
    }
}
