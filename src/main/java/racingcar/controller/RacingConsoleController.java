package racingcar.controller;

import racingcar.exception.CustomException;
import racingcar.exception.MaxAttemptInputException;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.track.Track;
import racingcar.view.inputview.InputView;
import racingcar.view.outputview.OutputView;

public class RacingConsoleController {

    private static final int MAX_ATTEMPT_COUNT = 5;

    private final InputView inputView;
    private final OutputView outputView;

    public RacingConsoleController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final MovingStrategy movingStrategy) {
        Cars cars = makeCars(movingStrategy);
        Track track = makeTrack(cars);

        outputView.printCurrentCarsPosition(cars);
        startRace(track);
        concludeWinner(track);
    }

    private Cars makeCars(final MovingStrategy movingStrategy) {
        for (int count = 0; count < MAX_ATTEMPT_COUNT; count++) {
            try {
                return new Cars(inputView.inputCarNames(), movingStrategy);
            } catch (CustomException customException) {
                terminated(customException);
            }
        }

        throw new MaxAttemptInputException();
    }

    private Track makeTrack(final Cars cars) {
        for (int count = 0; count < MAX_ATTEMPT_COUNT; count++) {
            try {
                String trialTimes = inputView.inputTrialTimes();
                return new Track(cars, trialTimes);
            } catch (CustomException customException) {
                terminated(customException);
            }
        }

        throw new MaxAttemptInputException();
    }

    public void startRace(final Track track) {
        while (track.runnable()) {
            Cars cars = track.race();
            outputView.printCurrentCarsPosition(cars);
        }
    }

    public void concludeWinner(final Track track) {
        outputView.printWinnerCars(track.getCars());
    }

    public void terminated(final CustomException customException) {
        outputView.printErrorMessage(customException.getErrorNumber());
    }
}
