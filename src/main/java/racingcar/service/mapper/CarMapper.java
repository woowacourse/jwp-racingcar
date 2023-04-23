package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public List<CarEntity> mapToCarEntitiesFrom(final RacingGame racingGame,
                                                final Long savedId) {
        return racingGame.getParticipantAllCar()
                         .stream()
                         .map(it -> mapToCarEntityFrom(racingGame, savedId, it))
                         .collect(Collectors.toList());
    }

    private static CarEntity mapToCarEntityFrom(final RacingGame racingGame,
                                                final Long savedId,
                                                final Car car) {
        return new CarEntity(
                car.getName(),
                car.getPosition(),
                savedId,
                racingGame.isWinner(car),
                LocalDateTime.now()
        );
    }
}
