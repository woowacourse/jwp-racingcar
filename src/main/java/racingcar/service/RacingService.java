package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.CarResponse;
import racingcar.controller.dto.TrackRequest;
import racingcar.controller.dto.TrackResponse;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.track.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RacingService {

    private final RacingDao racingDao;
    private final MovingStrategy movingStrategy;

    public RacingService(final RacingDao racingDao, final MovingStrategy movingStrategy) {
        this.racingDao = racingDao;
        this.movingStrategy = movingStrategy;
    }

    @Transactional
    public TrackResponse play(final TrackRequest trackRequest) {
        final String names = trackRequest.getNames();
        final String trialTimes = trackRequest.getCount();

        final Cars cars = makeCars(names, movingStrategy);
        final Track track = makeTrack(cars, trialTimes);

        final Integer trackId = saveTrack(track);
        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);

        return makeResponse(finishedCars);
    }

    private Cars makeCars(final String name, final MovingStrategy movingStrategy) {
        return Cars.of(name, movingStrategy);
    }

    private Track makeTrack(final Cars cars, final String trialTimes) {
        return Track.of(cars, trialTimes);
    }

    private Cars startRace(final Track track) {
        while (track.runnable()) {
            track.race();
        }

        return track.getCars();
    }

    private Integer saveTrack(final Track track) {
        return racingDao.save(new TrackDto(track.getTrialTimes()));
    }

    private void saveCars(final Integer trackId, final Cars finishedCars) {
        final List<Car> winnerCars = finishedCars.getWinnerCars();
        final List<Car> carsCurrentInfo = finishedCars.getCarsCurrentInfo();

        for (final Car car : carsCurrentInfo) {
            racingDao.save(new CarDto(car.getCarName(), car.getPosition(), winnerCars.contains(car), trackId));
        }
    }

    private TrackResponse makeResponse(final Cars finishedCars) {
        final String winnerCarNames = makeWinnerCarNames(finishedCars);
        final List<CarResponse> results = makeCarResponses(finishedCars);
        return new TrackResponse(winnerCarNames, results);
    }

    private String makeWinnerCarNames(final Cars finishedCars) {
        final String winnerCarNames = finishedCars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));
        return winnerCarNames;
    }

    private List<CarResponse> makeCarResponses(final Cars finishedCars) {
        final List<CarResponse> results = finishedCars.getCarsCurrentInfo().stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
        return results;
    }

    public List<TrackResponse> findAll() {
        final List<TrackResponse> trackResponses = new ArrayList<>();
        final int maxId = racingDao.findMaxId().orElse(0);

        for (int id = 1; id <= maxId; id++) {
            final Cars cars = Cars.from(racingDao.findAllById(id));
            final String winners = makeWinnerCarNames(cars);
            final List<CarResponse> racingCars = makeCarResponses(cars);

            trackResponses.add(new TrackResponse(winners, racingCars));
        }

        return trackResponses;
    }
}
