package racingcar.domain;

import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ConsoleRacingGameRepository implements RacingGameRepository {
    @Override
    public Long save(final RacingGame racingGame) {
        return null;
    }

    @Override
    public Optional<RacingGame> findById(final Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, List<CarEntity>> findAllCars() {
        return null;
    }

    @Override
    public Map<Long, List<WinnerEntity>> findAllWinners() {
        return null;
    }
}
