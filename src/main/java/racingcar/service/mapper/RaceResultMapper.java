package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;
import racingcar.entity.RaceResultEntity;
import racingcar.service.dto.CarStatusResponse;
import racingcar.service.dto.RaceResultResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class RaceResultMapper {

    private static final String CAR_NAMES_DELIMITER = ",";

    public RaceResultEntity mapToRaceResultEntity(final RacingGame racingGame) {
        return new RaceResultEntity(racingGame.getTrialCount(),
                                    LocalDateTime.now());
    }

    public RaceResultResponse mapToRaceResultResponse(final RacingGame racingGame) {
        final List<CarStatusResponse> carStatusResponses = mapToCarStatusResponseFrom(racingGame);
        final String winners = mapToWinnersNameFrom(racingGame);

        return new RaceResultResponse(winners, carStatusResponses);
    }

    private List<CarStatusResponse> mapToCarStatusResponseFrom(final RacingGame racingGame) {
        return racingGame.getParticipantAllCar()
                         .stream()
                         .map(car -> new CarStatusResponse(car.getName(),
                                                           car.getPosition()))
                         .collect(toList());
    }

    private String mapToWinnersNameFrom(final RacingGame racingGame) {
        return racingGame.determineWinners()
                         .stream()
                         .map(Car::getName)
                         .collect(Collectors.joining(","));
    }

    public List<RaceResultResponse> mapToRaceResultResponses(final List<CarEntity> carEntities) {
        final Map<Long, List<CarEntity>> groupingByRaceResultId =
                carEntities.stream()
                           .collect(Collectors.groupingBy(
                                   CarEntity::getRaceResultId)
                           );

        return groupingByRaceResultId.values()
                                     .stream()
                                     .map(it -> new RaceResultResponse(
                                             mapToWinnersNameFrom(it),
                                             mapToCarStatusResponses(it))
                                     )
                                     .collect(toList());
    }

    private String mapToWinnersNameFrom(final List<CarEntity> carEntities) {
        return carEntities.stream()
                          .filter(CarEntity::isWinner)
                          .map(CarEntity::getName)
                          .collect(Collectors.joining(CAR_NAMES_DELIMITER));
    }

    private List<CarStatusResponse> mapToCarStatusResponses(final List<CarEntity> carEntities) {
        return carEntities.stream()
                          .map(it -> new CarStatusResponse(it.getName(), it.getPosition()))
                          .collect(toList());
    }
}
