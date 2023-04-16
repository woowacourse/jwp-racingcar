package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingCarController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = generateCars(inputView.readCarNames());
        int round = inputView.readRacingRound();
        playGame(cars, round);
    }

    private Cars generateCars(List<String> carNames) {
        List<Car> carInstances = new ArrayList<>();
        for (String name : carNames) {
            carInstances.add(new Car(name));
        }
        return new Cars(carInstances);
    }

    private void playGame(Cars cars, int round) {
        RacingGame racingGame = new RacingGame(cars, round);
        racingGame.play();
        outputView.printFinalResult(racingGame.findWinnerCars());
    }
}
