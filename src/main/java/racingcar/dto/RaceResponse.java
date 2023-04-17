package racingcar.dto;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;

public class RaceResponse {

    private static final String JOIN_DELIMITER = ",";
    private final String winners;
    private final List<CarStatusDto> racingCars;

    private RaceResponse(final String winners, final List<CarStatusDto> racingCars) {
        this.winners = winners;
        this.racingCars = racingCars;
    }

    public static RaceResponse create(final List<String> winners,
        final List<CarStatusDto> carRaceResult) {
        final String winnerNames = String.join(JOIN_DELIMITER, winners);
        return new RaceResponse(winnerNames, carRaceResult);
    }

    public static RaceResponse of(final RaceEntity raceEntity, final List<CarEntity> carEntities) {
        final List<CarStatusDto> carStatusDtos = carEntities.stream()
            .map(CarStatusDto::of)
            .collect(Collectors.toUnmodifiableList());
        return new RaceResponse(raceEntity.getWinners(), carStatusDtos);
    }

    public String getWinners() {
        return winners;
    }

    public List<CarStatusDto> getRacingCars() {
        return racingCars;
    }
}
