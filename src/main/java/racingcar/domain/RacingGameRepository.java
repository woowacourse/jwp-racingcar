package racingcar.domain;

import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RacingGameRepository {

    Long save(final RacingGame racingGame);

    Optional<RacingGame> findById(final Long id);

    Map<Long, List<CarEntity>> findAllCars();

    Map<Long, List<WinnerEntity>> findAllWinners();
}
