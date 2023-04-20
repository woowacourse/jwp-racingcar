package racingcar.controller;

import racingcar.domain.carfactory.CarFactory;
import racingcar.domain.cars.Cars;
import racingcar.domain.numbergenerator.RandomSingleDigitGenerator;
import racingcar.domain.record.GameRecorder;
import racingcar.domain.system.GameSystem;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = makeCars(inputView.readCarNames());
        GameSystem gameSystem = new GameSystem(inputView.readGameRound(), new GameRecorder(new ArrayList<>()));
        gameSystem.executeRace(cars, new RandomSingleDigitGenerator());
        outputView.printRacingResult(gameSystem.getWinnersGameResult(), gameSystem.getFinalGameResult());
    }

    private Cars makeCars(final List<String> carNames) {
        CarFactory carFactory = new CarFactory();
        return carFactory.createCars(carNames);
    }
}
