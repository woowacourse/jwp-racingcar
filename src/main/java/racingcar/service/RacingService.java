package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.CarResponse;
import racingcar.controller.TrackCreateResponse;
import racingcar.controller.TrackReadResponse;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;
import racingcar.model.track.Track;

@Service
@Transactional(readOnly = true)
public class RacingService {

    private final RacingDao racingDao;
    private final MovingStrategy movingStrategy = new RandomMovingStrategy();

    public RacingService(final RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    @Transactional
    public TrackCreateResponse play(final List<String> names, final Integer trialTimes) {
        final Cars cars = new Cars(names, movingStrategy);
        final Track track = new Track(cars, trialTimes);
        final Integer trackId = saveTrack(track);

        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);
        return new TrackCreateResponse(makeWinnerCarNames(finishedCars), makeCarResponses(finishedCars));
    }

    public Cars startRace(final Track track) {
        while (track.runnable()) {
            track.race();
        }
        return track.getCars();
    }

    private Integer saveTrack(final Track track) {
        return racingDao.saveTrack(new TrackDto(track.getTrialTimes()));
    }

    private void saveCars(final Integer trackId, final Cars finishedCars) {
        final List<Car> winnerCars = finishedCars.getWinnerCars();
        final List<Car> carsCurrentInfo = finishedCars.getCarsCurrentInfo();

        List<CarDto> carDtos = carsCurrentInfo.stream()
                .map(car -> new CarDto(car.getCarName(), car.getPosition(), winnerCars.contains(car), trackId))
                .collect(Collectors.toList());

        racingDao.saveWithBatch(carDtos);
    }

    public TrackReadResponse findById(int trackId) {
        Cars cars = racingDao.findAllCarsByTrackId(trackId);
        String winners = makeWinnerCarNames(cars);
        List<CarResponse> carResponses = makeCarResponses(cars);
        return new TrackReadResponse(winners, carResponses);
    }

    public List<TrackReadResponse> findAllResults() {
        List<TrackReadResponse> trackReadResponses = new ArrayList<>();

        List<Integer> trackIds = racingDao.findAllTrackIds();
        for (Integer trackId : trackIds) {
            Cars cars = racingDao.findAllCarsByTrackId(trackId);
            String winners = makeWinnerCarNames(cars);
            List<CarResponse> carResponses = makeCarResponses(cars);
            trackReadResponses.add(new TrackReadResponse(winners, carResponses));
        }
        return trackReadResponses;
    }

    private static String makeWinnerCarNames(final Cars finishedCars) {
        return finishedCars.getWinnerCars().stream()
                .map(Car::getCarName)
                .collect(Collectors.joining(", "));
    }

    private static List<CarResponse> makeCarResponses(final Cars finishedCars) {
        return finishedCars.getCarsCurrentInfo().stream()
                .map(car -> new CarResponse(car.getCarName(), car.getPosition()))
                .collect(Collectors.toList());
    }
}
