package racingcar.dao;

import racingcar.dto.RacingGameDto;

import java.util.List;

public interface RacingGameRepository {

    void save(RacingGameDto racingGameDto);

    List<RacingGameDto> selectAll();
}
