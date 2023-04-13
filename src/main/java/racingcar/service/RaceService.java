package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.RaceResult;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.repository.CarRaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final CarRaceRepository carRaceRepository;

    public RaceService(final NumberGenerator numberGenerator, final CarRaceRepository carRaceRepository) {
        this.numberGenerator = numberGenerator;
        this.carRaceRepository = carRaceRepository;
    }

    @Transactional
    public RaceResponse play(final RaceRequest raceRequest) {
        final Race race = Race.create(raceRequest.getCount());
        final Cars cars = Cars.create(raceRequest.getNames(), numberGenerator);
        final RaceResponse raceResponse = getRaceResult(race, cars);
        final RaceResult raceResult = new RaceResult(raceRequest.getCount(), raceResponse.getWinners(), cars);
        carRaceRepository.save(raceResult);
        return raceResponse;
    }

    private RaceResponse getRaceResult(final Race race, final Cars cars) {
        int tryCount = 0;
        while (race.isRunning(tryCount++)) {
            cars.race();
        }
        return makeRaceResponse(cars);
    }

    private RaceResponse makeRaceResponse(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = cars.getCars()
                .stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return RaceResponse.create(winners, carRaceResult);
    }
}
