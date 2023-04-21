package racingcar.dao;

import racingcar.dto.PlayerFindDto;
import racingcar.entity.Player;

import java.util.List;

interface PlayerDao {

    void save(final long gameId, final List<Player> players);

    List<PlayerFindDto> findById(final long gameId);
}
