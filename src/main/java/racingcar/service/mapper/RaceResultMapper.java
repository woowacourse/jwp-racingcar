package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RaceResultMapper {

    private static final String CAR_NAMES_DELIMITER = ",";

    public RaceResultEntity mapToRaceResultEntity(final RacingGame racingGame) {
        return new RaceResultEntity(racingGame.getTrialCount(),
                                    String.join(CAR_NAMES_DELIMITER, mapToWinnersNameFrom(racingGame)),
                                    LocalDateTime.now());
    }

    private List<String> mapToWinnersNameFrom(final RacingGame racingGame) {
        return racingGame.determineWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.toList());
    }

    public List<CarStatusResponse> mapToCarStatusResponseFrom(final RacingGame racingGame) {
        return racingGame.getParticipantAllCar()
                         .stream()
                         .map(car -> new CarStatusResponse(car.getName(),
                                                           car.getPosition()))
                         .collect(Collectors.toList());
    }

    public List<CarStatusResponse> mapToCarStatusResponseFrom(final List<CarEntity> carEntities) {
        return carEntities.stream()
                          .map(entity -> new CarStatusResponse(entity.getName(),
                                                               entity.getPosition()))
                          .collect(Collectors.toList());
    }
}
