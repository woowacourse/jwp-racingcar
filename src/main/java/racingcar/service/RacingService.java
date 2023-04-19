package racingcar.service;

import java.util.List;
import java.util.Set;
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
    public List<TrackResponse> findAllCars() {
        List<CarDto> carResults = racingDao.findAll();
        Set<Integer> trackIds = toTrackIds(carResults);

        return trackIds.stream()
                .map(trackId -> {
                    String winners = toWinnerNamesByTrackId(carResults, trackId);
                    List<CarResponse> collect = toCarResponseByTrackId(carResults, trackId);
                    return new TrackResponse(winners, collect);
                })
                .collect(Collectors.toList());
    }

    private Set<Integer> toTrackIds(List<CarDto> carDtos) {
        return carDtos.stream()
                .map(CarDto::getTrackId)
                .collect(Collectors.toSet());
    }

    private String toWinnerNamesByTrackId(List<CarDto> carDtos, Integer trackId) {
        return carDtos.stream()
                .filter(carDto -> carDto.getTrackId() == trackId)
                .filter(carDto -> carDto.getIsWinner())
                .map(CarDto::getName)
                .collect(Collectors.joining(","));
    }

    private List<CarResponse> toCarResponseByTrackId(List<CarDto> carDtos, Integer trackId) {
        return carDtos.stream()
                .filter(carDto -> carDto.getTrackId() == trackId)
                .map(carDto -> new CarResponse(carDto.getName(), carDto.getPosition()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Cars play(final String names, final String trialTimes) {
        final Cars cars = makeCars(names, movingStrategy);
        final Track track = makeTrack(cars, trialTimes);
        final Integer trackId = saveTrack(track);

        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);

        return finishedCars;
    }

    private Cars makeCars(final String name, final MovingStrategy movingStrategy) {
        return new Cars(name, movingStrategy);
    }

    private Track makeTrack(final Cars cars, final String trialTimes) {
        return new Track(cars, trialTimes);
    }

    public Cars startRace(final Track track) {
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
}
