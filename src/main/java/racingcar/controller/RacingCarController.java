package racingcar.controller;

import static java.util.stream.Collectors.*;

import java.util.List;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingCars;
import racingcar.utils.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    public void run() {
        RacingCars racingCars = createRacingCars();
        int roundCount = createRoundCount();
        startRound(racingCars, roundCount);
    }

    private RacingCars createRacingCars() {
        try {
            List<String> carNames = inputCarNames();
            return carNames.stream()
                    .map(Car::new)
                    .collect(collectingAndThen(toList(), RacingCars::new));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createRacingCars();
        }
    }

    private List<String> inputCarNames() {
        OutputView.printCarNameRequestMsg();
        return InputView.readCarNames();
    }


    private int createRoundCount() {
        try {
            return inputRoundCount();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return createRoundCount();
        }
    }

    private int inputRoundCount() {
        OutputView.printRoundCountRequestMsg();
        return InputView.readRoundCount();
    }

    private void startRound(RacingCars racingCars, int roundCount) {
        RacingGame racingGame = new RacingGame(new RandomNumberGenerator(), racingCars);
        OutputView.printRoundResultMsg();
        OutputView.printRoundState(racingCars.getCars());
        for (int i = 0; i < roundCount; i++) {
            racingGame.moveCars();
            OutputView.printRoundState(racingCars.getCars());
        }
        OutputView.printRacingResult(racingGame.getWinners());
    }
}
