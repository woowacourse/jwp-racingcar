package racingcar.dao;

import racingcar.dao.mapper.PlayerDtoMapper;
import racingcar.domain.CarGroup;

import java.util.List;

public interface PlayerDao {

    boolean save(final CarGroup carGroup, final int racingGameId);

    List<PlayerDtoMapper> findAllById(final int id);
}
