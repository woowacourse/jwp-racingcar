package racingcar.dao;

import java.util.List;
import java.util.Optional;

import racingcar.dao.dto.RacingGameDto;

public interface RacingGameDao {

    int save(final String winners, final int count);

    Optional<RacingGameDto> findById(final int id);

    List<RacingGameDto> findAll();
}
