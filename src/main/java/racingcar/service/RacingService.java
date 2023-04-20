package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.CarResponse;
import racingcar.controller.TrackResponse;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.car.strategy.RandomMovingStrategy;
import racingcar.model.track.Track;

@Service
public class RacingService {

    private final RacingDao racingDao;
    private final MovingStrategy movingStrategy = new RandomMovingStrategy();

    public RacingService(final RacingDao racingDao) {
        this.racingDao = racingDao;
    }

    @Transactional
    public TrackResponse play(final String names, final Integer trialTimes) {
        final Cars cars = makeCars(names, movingStrategy);
        final Track track = makeTrack(cars, trialTimes);
        final Integer trackId = saveTrack(track);

        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);
        return new TrackResponse(makeWinnerCarNames(finishedCars), makeCarResponses(finishedCars));
    }

    private Cars makeCars(final String name, final MovingStrategy movingStrategy) {
        return new Cars(name, movingStrategy);
    }

    private Track makeTrack(final Cars cars, final Integer trialTimes) {
        return new Track(cars, trialTimes);
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

    public List<TrackResponse> findAllResults() {
        List<TrackResponse> trackResponses = new ArrayList<>();

        List<Integer> trackIds = racingDao.findAllTrackIds();
        for (Integer trackId : trackIds) {
            Cars cars = racingDao.findAllCarsByTrackId(trackId);
            String winners = makeWinnerCarNames(cars);
            List<CarResponse> carResponses = makeCarResponses(cars);
            trackResponses.add(new TrackResponse(winners, carResponses));
        }
        return trackResponses;
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
