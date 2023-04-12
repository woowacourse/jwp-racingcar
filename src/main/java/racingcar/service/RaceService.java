package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceService {

    private final NumberGenerator numberGenerator;

    public RaceService(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public RaceResponse getRaceResults(final RaceRequest raceRequest) {
        final Race race = Race.create(raceRequest.getCount());
        final Cars cars = Cars.create(raceRequest.getNames(), numberGenerator);
        return makeRaceResults(race, cars);
    }

    private RaceResponse makeRaceResults(final Race race, final Cars cars) {
        int tryCount = 0;
        while (race.isRunning(tryCount++)) {
            startRace(cars);
        }
        return getRaceResult(cars);
    }

    private void startRace(final Cars cars) {
        cars.race();
    }

    private RaceResponse getRaceResult(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = cars.getCars()
                .stream()
                .map(car -> new CarStatusDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return RaceResponse.create(winners, carRaceResult);
    }
}
