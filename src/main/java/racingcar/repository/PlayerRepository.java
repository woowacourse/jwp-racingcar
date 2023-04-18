package racingcar.repository;

import racingcar.domain.Cars;
import racingcar.repository.mapper.PlayerMapper;

import java.util.List;

public interface PlayerRepository {
    boolean save(final Cars cars, final int racingGameId);

    List<PlayerMapper> findBy(final int racingGameId);
}
