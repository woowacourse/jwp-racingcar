package racingcar.dao;

import java.util.List;

import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.domain.CarGroup;

public interface PlayerDao {

    boolean save(final CarGroup carGroup, final int racingGameId);

    List<PlayerDtoMapper> findAllByRacingGameId(final int id);
}
