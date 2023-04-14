package racingcar.repository;

import java.util.Optional;

import racingcar.repository.mapper.RacingGameInfo;

public interface RacingGameRepository {

    int save(final String winners, final int count);

    Optional<RacingGameInfo> findById(final int id);
}
