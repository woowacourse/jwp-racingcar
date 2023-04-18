package racingcar.repository;

import racingcar.repository.mapper.RacingGameMapper;

import java.util.List;
import java.util.Optional;

public interface RacingGameRepository {
    int save(final String winners, final int count);

    Optional<RacingGameMapper> findById(final int id);

    List<RacingGameMapper> findAll();
}
