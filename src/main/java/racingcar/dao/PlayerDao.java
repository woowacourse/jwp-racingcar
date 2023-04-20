package racingcar.dao;

import racingcar.dto.PlayerFindDto;
import racingcar.dto.PlayerSaveDto;

import java.util.List;

interface PlayerDao {

    void save(final long gameId, final List<PlayerSaveDto> playerSaveDtos);

    List<PlayerFindDto> findById(final long gameId);
}
