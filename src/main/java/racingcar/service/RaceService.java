package racingcar.service;

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
        final Race race = new Race(raceRequest.getCount());
        final Cars cars = makeCars(raceRequest);
        final RaceResponse raceResponse = getRaceResult(race, cars);
        final Long raceResultId = raceResultDao.save(raceRequest.getCount(),
            raceRequest.getNames());
        carDao.saveAll(raceResultId, cars.getCars());
        return raceResponse;
    }

    private Cars makeCars(RaceRequest raceRequest) {
        final List<Car> cars = raceRequest.getCarNames()
            .stream()
            .map(Car::create)
            .collect(Collectors.toUnmodifiableList());
        return new Cars(cars, numberGenerator);
    }

    private RaceResponse getRaceResult(final Race race, final Cars cars) {
        return makeRaceResponse(race.run(cars));
    }

    private RaceResponse makeRaceResponse(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = cars.getCars()
            .stream()
            .map(car -> new CarStatusDto(car.getCarName(), car.getCarPosition()))
            .collect(Collectors.toUnmodifiableList());
        return RaceResponse.create(winners, carRaceResult);
    }
}
