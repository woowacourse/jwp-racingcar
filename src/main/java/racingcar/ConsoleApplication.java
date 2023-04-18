package racingcar;

import java.util.List;
import java.util.Scanner;

import racingcar.controller.ConsoleController;
import racingcar.service.ConsoleService;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            OutputView outputView = new OutputView();
            InputView inputView = new InputView(scanner);
            outputView.startRacing();

            ConsoleService service = getService(inputView);
            ConsoleController controller = getController(service, outputView);

            int tryCount = getTryCount(inputView, outputView);

            controller.run(tryCount);
        }
    }

    private static int getTryCount(InputView inputView, OutputView outputView) {
        outputView.tryCount();

        return inputView.tryCount();
    }

    private static ConsoleController getController(ConsoleService service, OutputView outputView) {
        return new ConsoleController(outputView, service);
    }

    private static ConsoleService getService(InputView inputView) {
        try {
            List<String> carNames = inputView.carNames();

            return new ConsoleService(carNames);
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());

            return getService(inputView);
        }
    }
}
