package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.controller.dto.TrackRequest;
import racingcar.controller.dto.TrackResponse;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.CarDto;
import racingcar.dao.dto.TrackDto;
import racingcar.mapper.CarDtoMapper;
import racingcar.mapper.TrackDtoMapper;
import racingcar.mapper.TrackResponseMapper;
import racingcar.model.TrialTimes;
import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.car.strategy.MovingStrategy;
import racingcar.model.track.Track;

import java.util.ArrayList;
import java.util.List;

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
        final String count = trackRequest.getCount();

        final Cars cars = Cars.of(names);
        final TrialTimes trialTimes = TrialTimes.from(count);
        final Track track = Track.of(cars, trialTimes, movingStrategy);

        final Integer trackId = saveTrack(track);
        final Cars finishedCars = startRace(track);
        saveCars(trackId, finishedCars);

        return TrackResponseMapper.from(finishedCars);
    }

    private Cars startRace(final Track track) {
        while (track.runnable()) {
            track.race();
        }

        return track.getCars();
    }

    private Integer saveTrack(final Track track) {
        final TrialTimes trialTimes = track.getTrialTimes();
        final TrackDto trackDto = TrackDtoMapper.from(trialTimes);

        return racingDao.save(trackDto);
    }

    private void saveCars(final Integer trackId, final Cars finishedCars) {
        final List<Car> winnerCars = finishedCars.getWinnerCars();
        final List<Car> carsCurrentInfo = finishedCars.getCarsCurrentInfo();

        for (final Car car : carsCurrentInfo) {
            final CarDto carDto = CarDtoMapper.of(car.getCarName(), car.getPosition(), winnerCars.contains(car), trackId);
            racingDao.save(carDto);
        }
    }

    public List<TrackResponse> findAll() {
        final List<TrackResponse> trackResponses = new ArrayList<>();
        final List<Integer> gameIds = racingDao.findAllId();

        for (int id: gameIds) {
            final Cars cars = Cars.from(racingDao.findAllById(id));
            final TrackResponse response = TrackResponseMapper.from(cars);

            trackResponses.add(response);
        }

        return trackResponses;
    }
}
