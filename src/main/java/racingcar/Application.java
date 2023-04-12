package racingcar;

import racingcar.controller.ConsoleController;
import racingcar.domain.Cars;
import racingcar.utils.RandomNumberGenerator;
import racingcar.utils.ScannerInputReader;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(new ScannerInputReader());

        Cars cars = new Cars(new ArrayList<>(), RandomNumberGenerator.makeInstance());

        ConsoleController service = new ConsoleController(outputView, inputView, cars);
        service.run();
    }
}
