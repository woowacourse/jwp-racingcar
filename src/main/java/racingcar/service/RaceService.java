package racingcar.service;

import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.domain.dto.CarStatusDto;
import racingcar.domain.dto.RaceResultDto;
import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RaceService {

    private final NumberGenerator numberGenerator;

    public RaceService(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Race createRace(final String raceCount) {
        return Race.create(raceCount);
    }

    public List<RaceResultDto> getRaceResults(final String carNames, final Race race) {
        final Cars cars = Cars.create(carNames, numberGenerator);
        final List<RaceResultDto> raceResults = new ArrayList<>();
        int raceCount = 0;

        while (race.isRunning(raceCount++)) {
            startRace(cars);
            RaceResultDto raceResult = getRaceResult(cars);
            raceResults.add(raceResult);
        }
        return raceResults;
    }

    private void startRace(final Cars cars) {
        cars.race();
    }

    private RaceResultDto getRaceResult(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarStatusDto> carRaceResult = cars.getCars()
                .stream()
                .map(car -> CarStatusDto.create(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
        return RaceResultDto.create(winners, carRaceResult);
    }
}
