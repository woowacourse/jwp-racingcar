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
    private final MovingStrategy movingStrategy;

    public RacingConsoleController(final InputView inputView,
                                   final OutputView outputView,
                                   final MovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public void start() {
        final TrackRequest trackRequest = makeTrackRequest();

        final Track track = makeTrack(trackRequest);

        startRace(track);
        concludeWinner(track);
    }

    private TrackRequest makeTrackRequest() {
        final String carNames = inputView.inputCarNames();
        final String trialTimes = inputView.inputTrialTimes();

        return new TrackRequest(carNames, trialTimes);
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

    private Track makeTrack(final TrackRequest trackRequest) {
        final String carNames = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        final Cars cars = new Cars(carNames, movingStrategy);
        final Track track = new Track(cars, trialTimes);

        return track;
    }

    public void startRace(final Track track) {
        while (track.runnable()) {
            track.race();
        }
    }

    public void concludeWinner(final Track track) {
        outputView.printWinnerCars(track.getCars());
        outputView.printCurrentCarsPosition(track.getCars());
    }

    public void terminated(final CustomException customException) {
        outputView.printErrorMessage(customException.getErrorNumber());
    }
}
