package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;
import racingcar.view.InputView;
import racingcar.view.MessageView;
import racingcar.view.OutputView;


public class Controller {

    private final Validation validation = new Validation();
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
            validation.validateCarNames(carNames);
            return racingCarService.makeCars(carNames);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return initCars();
        }
    }

    private int setTryCount() {
        MessageView.printTryCountMessage();

        try {
            int tryCount = InputView.inputTryCount();
            validation.validateTryCount(tryCount);
            return tryCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return setTryCount();
    }
}
