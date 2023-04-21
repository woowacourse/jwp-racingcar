package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.MessageView;
import racingcar.view.OutputView;


public class ConsoleController {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private final RacingCarService racingCarService = new RacingCarService();

    public void runGame() {
        Cars cars = initCars();
        racingCarService.playRacing(cars, setTryCount(), randomNumberGenerator);
        OutputView.printResult(cars);
    }

    private Cars initCars() {
        MessageView.printCarNameMessage();

        try {
            String carNames = InputView.inputCarNames();
            return racingCarService.makeCars(carNames);
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            return initCars();
        }
    }

    private int setTryCount() {
        MessageView.printTryCountMessage();

        try {
            return InputView.inputTryCount();
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            return setTryCount();
        }
    }
}
