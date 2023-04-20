package racingcar.console.controller;

import racingcar.web.domain.Cars;
import racingcar.web.service.RacingCarService;
import racingcar.web.util.RandomNumberGenerator;
import racingcar.console.view.InputView;
import racingcar.console.view.MessageView;
import racingcar.console.view.OutputView;


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
            System.out.println(e.getMessage());
            return initCars();
        }
    }

    private int setTryCount() {
        MessageView.printTryCountMessage();

        try {
            return InputView.inputTryCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return setTryCount();
    }
}
