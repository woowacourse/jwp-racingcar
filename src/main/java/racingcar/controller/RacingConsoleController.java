package racingcar.controller;

import racingcar.controller.dto.TrackRequest;
import racingcar.exception.CustomException;
import racingcar.mapper.TrackRequestMapper;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.track.Track;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {

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

        return TrackRequestMapper.of(carNames, trialTimes);
    }

    private Track makeTrack(final TrackRequest trackRequest) {
        final String carNames = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        final Cars cars = Cars.of(carNames);
        final Track track = Track.of(cars, trialTimes, movingStrategy);

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
        outputView.printErrorMessage(customException);
    }
}
