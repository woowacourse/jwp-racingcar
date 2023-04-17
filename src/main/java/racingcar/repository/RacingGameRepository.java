package racingcar.repository;

import racingcar.repository.mapper.RacingGameInfo;

import java.util.Optional;

public interface RacingGameRepository {
    int save(final String winners, final int count);

    Optional<RacingGameInfo> findById(final int id);
}
