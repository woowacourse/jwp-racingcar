package racingcar.controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.dto.response.RacingGameResponse;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private static final String TRY_COUNT_OVER_EXCEPTION_MESSAGE = "재시도 횟수를 초과하여, 어플리케이션을 종료합니다.";
    private static final String NAME_DELIMITER = ",";

    private final NumberGenerator numberGenerator;
    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleRacingCarController(NumberGenerator numberGenerator,
                                      InputView inputView,
                                      OutputView outputView) {
        this.numberGenerator = numberGenerator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Cars cars = getCars();
        int round = getRound();
        RacingGame racingGame = new RacingGame(cars, round);
        racingGame.play(numberGenerator);

        printResult(racingGame);
    }

    private Cars getCars() {
        TryCount tryCount = new TryCount();

        while (tryCount.isRunnable()) {
            try {
                String names = inputView.readCarNames();
                return Arrays.stream(names.split(NAME_DELIMITER))
                        .map(Car::new)
                        .collect(collectingAndThen(toList(), Cars::new));
            } catch (IllegalArgumentException exception) {
                outputView.printExceptionMessage(exception.getMessage());
                tryCount.increase();
            }
        }

        throw new IllegalArgumentException(TRY_COUNT_OVER_EXCEPTION_MESSAGE);
    }

    private int getRound() {
        TryCount tryCount = new TryCount();

        while (tryCount.isRunnable()) {
            try {
                return inputView.readRacingRound();
            } catch (IllegalArgumentException exception) {
                outputView.printExceptionMessage(exception.getMessage());
                tryCount.increase();
            }
        }

        throw new IllegalArgumentException(TRY_COUNT_OVER_EXCEPTION_MESSAGE);
    }

    private void printResult(RacingGame racingGame) {
        RacingGameResponse racingGameResponse = RacingGameResponse.createByCars(racingGame.getCars(),
                racingGame.findWinnerCars());
        outputView.printResultMessage(racingGameResponse);
    }
}
