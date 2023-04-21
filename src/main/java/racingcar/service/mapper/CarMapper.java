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
                                                final Integer savedId) {

        return racingGame.getParticipantAllCar()
                         .stream()
                         .map(it -> mapToCarEntity(savedId, it))
                         .collect(Collectors.toList());
    }

    private CarEntity mapToCarEntity(final Integer savedId, final Car car) {
        return new CarEntity(car.getName(),
                             car.getPosition(),
                             savedId,
                             LocalDateTime.now());
    }
}
