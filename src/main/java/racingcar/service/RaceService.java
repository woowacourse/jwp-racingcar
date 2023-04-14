package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Car;
import racingcar.domain.CarName;
import racingcar.domain.CarPosition;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final CarDao carDao;
    private final RaceResultDao raceResultDao;

    public RaceService(final NumberGenerator numberGenerator, final CarDao carDao, final RaceResultDao raceResultDao) {
        this.numberGenerator = numberGenerator;
        this.carDao = carDao;
        this.raceResultDao = raceResultDao;
    }

    public RaceResponse play(final RaceRequest raceRequest) {
        final List<String> names = raceRequest.makeSplitNames();
        final Cars cars = new Cars(createCars(names), numberGenerator);
        final Race race = new Race(raceRequest.getCount());

        final Cars movedCars = race.run(cars);
        final RaceResponse raceResponse = makeRaceResponse(movedCars);
        final Long raceResultId = raceResultDao.save(raceRequest.getCount(), raceResponse.getWinners());
        carDao.saveAll(raceResultId, movedCars.getCars());
        return raceResponse;
    }

    private List<Car> createCars(final List<String> carNames) {
        return carNames.stream()
                .map(name -> Car.create(CarName.create(name), CarPosition.init()))
                .collect(Collectors.toUnmodifiableList());
    }

    private RaceResponse makeRaceResponse(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = makeCarRaceResult(cars);
        return RaceResponse.create(winners, carRaceResult);
    }

    private List<CarStatusDto> makeCarRaceResult(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
