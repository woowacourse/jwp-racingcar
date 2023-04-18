package racingcar.dao;

import racingcar.dao.mapper.RacingGameDtoMapper;

import java.util.List;
import java.util.Optional;

public interface RacingGameDao {

    int save(final String winners, final int count);

    Optional<RacingGameDtoMapper> findById(final int id);

    List<RacingGameDtoMapper> findAll();
}
