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
    public TrackResponse play(final String names, final String trialTimes) {
        final Cars cars = makeCars(names, movingStrategy);
        final Track track = makeTrack(cars, trialTimes);
        final Integer trackId = saveTrack(track);

        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);
        return TrackResponse.of(finishedCars);
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

        List<CarDto> carDtos = carsCurrentInfo.stream()
                .map(car -> new CarDto(car.getCarName(), car.getPosition(), winnerCars.contains(car), trackId))
                .collect(Collectors.toList());

        racingDao.saveWithBatch(carDtos);
    }

    public List<TrackResponse> findAllResults() {
        List<TrackResponse> trackResponses = new ArrayList<>();

        List<Integer> trackIds = racingDao.findAllTrackIds();
        for (Integer trackId : trackIds) {
            List<CarDto> carDtos = racingDao.findAllCarsByTrackId(trackId);
            String winners = carDtos.stream()
                    .filter(carDto -> carDto.getIsWinner() == true)
                    .map(CarDto::getName)
                    .collect(Collectors.joining(","));
            List<CarResponse> carResponses = carDtos.stream()
                    .map(caaDto -> new CarResponse(caaDto.getName(), caaDto.getPosition()))
                    .collect(Collectors.toList());
            trackResponses.add(new TrackResponse(winners, carResponses));
        }
        return trackResponses;
    }
}
