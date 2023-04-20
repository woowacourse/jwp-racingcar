package racingcar.dao;

import racingcar.dto.PlayerSaveDto;

import java.util.List;

interface PlayerDao {

    void save(final long gameId, final List<PlayerSaveDto> playerSaveDtos);

}
