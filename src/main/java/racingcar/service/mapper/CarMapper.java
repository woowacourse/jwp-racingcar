package racingcar.service.mapper;

import org.springframework.stereotype.Component;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.entity.CarEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public List<CarEntity> mapToCarEntitiesFrom(final RacingCars racingCars,
                                                final Integer savedId) {

        return racingCars.getCars()
                         .stream()
                         .map(it -> mapToCarEntity(savedId, it))
                         .collect(Collectors.toList());
    }

    private CarEntity mapToCarEntity(final Integer savedId, final Car it) {
        return new CarEntity(it.getName(),
                             it.getPosition(),
                             savedId,
                             LocalDateTime.now());
    }
}
