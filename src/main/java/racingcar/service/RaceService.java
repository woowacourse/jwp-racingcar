package racingcar.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.RaceResult;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.repository.CarRaceRepository;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final CarRaceRepository carRaceRepository;

    public RaceService(final NumberGenerator numberGenerator,
        final CarRaceRepository carRaceRepository) {
        this.numberGenerator = numberGenerator;
        this.carRaceRepository = carRaceRepository;
    }

    @Transactional
    public RaceResponse play(final RaceRequest raceRequest) {
        final Race race = new Race(raceRequest.getCount());
        final Cars cars = makeCars(raceRequest);
        final RaceResponse raceResponse = getRaceResult(race, cars);
        final RaceResult raceResult = new RaceResult(raceRequest.getCount(),
            raceResponse.getWinners(), cars);
        carRaceRepository.save(raceResult);
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
