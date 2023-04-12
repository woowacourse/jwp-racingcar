package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.dao.raceresult.dto.RaceResultRegisterRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceResultMapper {

    public RaceResultRegisterRequest convertToRaceResult(final int tryCount, final RacingCars racingCars) {

        final String joinedWinners = convertToNameFrom(racingCars).stream()
                                                                  .collect(Collectors.joining(","));

        return new RaceResultRegisterRequest(tryCount, joinedWinners);
    }

    private List<String> convertToNameFrom(RacingCars racingCars) {
        return racingCars.getWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }
}
