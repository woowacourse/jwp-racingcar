package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.CarGenerator;
import racingcar.domain.RacingGame;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.Input;
import racingcar.view.Output;

import java.util.List;

public class RacingGameConsoleController {
    private static final NumberGenerator numberGenerator = new RandomNumberGenerator();
    private static final CarGenerator carGenerator = new CarGenerator();

    private String[] carNames;
    private int tryCount;
    private RacingGame racingGame;
    private List<Car> cars;

    public void run() {
        init();
        racingGame = new RacingGame(cars, tryCount, this.numberGenerator);
        racingGame.run();
        Output.printWinner(racingGame.getWinner());
    }

    private void init() {
        Output.printMessage("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        this.carNames = Input.getCarNames(Input.getInput());
        Output.printMessage("시도할 회수는 몇회인가요?");
        this.tryCount = Input.getTryCount(Input.getInput());
        this.cars = carGenerator.generateCars(carNames);
    }
}
