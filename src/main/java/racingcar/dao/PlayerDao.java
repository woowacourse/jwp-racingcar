package racingcar.dao;

import java.util.List;

import racingcar.dao.dto.PlayerDto;
import racingcar.domain.CarGroup;

public interface PlayerDao {

    boolean save(final CarGroup carGroup, final int racingGameId);

    List<PlayerDto> findAllByRacingGameId(final int id);
}
