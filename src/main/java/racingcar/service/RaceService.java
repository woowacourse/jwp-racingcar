package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceResultEntity;
import racingcar.domain.repository.RaceResultEntityRepository;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final RaceResultEntityRepository raceResultEntityRepository;

    public RaceService(final NumberGenerator numberGenerator,
        final RaceResultEntityRepository raceResultEntityRepository) {
        this.numberGenerator = numberGenerator;
        this.raceResultEntityRepository = raceResultEntityRepository;
    }

    private static RaceResultEntity makeRaceResultEntity(final RaceRequest raceRequest,
        final Cars cars, final RaceResponse raceResponse) {
        final List<CarEntity> carEntities = cars.getCars()
            .stream()
            .map(car -> new CarEntity(null, car.getName(), car.getPosition()))
            .collect(Collectors.toUnmodifiableList());
        return new RaceResultEntity(null, raceRequest.getCount(),
            raceResponse.getWinners(), carEntities);
    }

    @Transactional
    public RaceResponse play(final RaceRequest raceRequest) {
        final Cars cars = run(raceRequest);
        final RaceResponse raceResponse = makeRaceResponse(cars);
        final RaceResultEntity raceResultEntity = makeRaceResultEntity(raceRequest, cars,
            raceResponse);
        raceResultEntityRepository.save(raceResultEntity);
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
        final List<RaceResultEntity> raceResultEntities = raceResultEntityRepository.findAll();
        return raceResultEntities.stream()
            .map(RaceResponse::of)
            .collect(Collectors.toUnmodifiableList());
    }
}
