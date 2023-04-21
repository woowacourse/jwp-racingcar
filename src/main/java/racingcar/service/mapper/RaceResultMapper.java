package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceResultMapper {

    private static final String CAR_NAMES_DELIMITER = ",";

    public RaceResultEntity mapToRaceResult(final int tryCount, final RacingCars racingCars) {
        return new RaceResultEntity(tryCount,
                                    String.join(CAR_NAMES_DELIMITER, mapToNameFrom(racingCars)),
                                    LocalDateTime.now());
    }

    private List<String> mapToNameFrom(RacingCars racingCars) {
        return racingCars.getWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }

    public List<CarStatusResponse> mapToCarStatus(final RacingCars racingCars) {
        return racingCars.getCars()
                         .stream()
                         .map(car -> new CarStatusResponse(car.getName(),
                                                           car.getPosition()))
                         .collect(Collectors.toList());
    }

    public List<CarStatusResponse> mapToCarStatus(final List<Car> cars) {
        return cars.stream()
                   .map(car -> new CarStatusResponse(car.getName(),
                                                     car.getPosition()))
                   .collect(Collectors.toList());
    }
}
