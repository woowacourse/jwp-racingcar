package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final CarDao carDao;
    private final RaceResultDao raceResultDao;

    public RaceService(final NumberGenerator numberGenerator, final CarDao carDao,
        final RaceResultDao raceResultDao) {
        this.numberGenerator = numberGenerator;
        this.carDao = carDao;
        this.raceResultDao = raceResultDao;
    }

    @Transactional
    public RaceResponse play(final RaceRequest raceRequest) {
        final Cars cars = run(raceRequest);
        final RaceResponse raceResponse = makeRaceResponse(cars);
        final Long raceResultId = raceResultDao.save(raceRequest.getCount(),
            raceResponse.getWinners());
        carDao.saveAll(raceResultId, cars.getCars());
        return raceResponse;
    }

    private Cars run(RaceRequest raceRequest) {
        final Race race = new Race(raceRequest.getCount());
        final Cars cars = makeCars(raceRequest.getCarNames());
        return race.run(cars);
    }

    private Cars makeCars(final List<String> carNames) {
        final List<Car> cars = carNames.stream()
            .map(Car::create)
            .collect(Collectors.toUnmodifiableList());
        return new Cars(cars, numberGenerator);
    }

    private RaceResponse makeRaceResponse(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = cars.getCars()
            .stream()
            .map(car -> CarStatusDto.of(car.getCarName(), car.getCarPosition()))
            .collect(Collectors.toUnmodifiableList());
        return RaceResponse.of(winners, carRaceResult);
    }

    public List<RaceResponse> findAllRace() {
        final List<RaceResponse> raceResponses = new ArrayList<>();
        final List<RaceEntity> raceEntities = raceResultDao.findAll();
        for (final RaceEntity raceEntity : raceEntities) {
            final List<CarEntity> carEntities = carDao.findAll(raceEntity.getId());
            raceResponses.add(RaceResponse.of(raceEntity, carEntities));
        }
        return raceResponses;
    }
}
