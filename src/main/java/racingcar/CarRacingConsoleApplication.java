package racingcar;

import racingcar.controller.CarRacingConsoleController;
import racingcar.dao.LocalCarDao;
import racingcar.dao.LocalGameDao;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.service.CarRacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.Scanner;

public class CarRacingConsoleApplication {

    public static void main(String[] args) {
        CarRacingConsoleController carRacingConsoleController = new CarRacingConsoleController(
                new InputView(new Scanner(System.in)),
                new OutputView(),
                new CarRacingService(new LocalGameDao(), new LocalCarDao(), new RandomSingleDigitGenerator()));

        carRacingConsoleController.play();
    }
}
