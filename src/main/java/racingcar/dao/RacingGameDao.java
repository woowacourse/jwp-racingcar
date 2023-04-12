package racingcar.dao;

import java.util.List;

public interface RacingGameDao {

    Long saveGame(final int trialCount);

    void saveAllPlayers(final List<PlayerSaveDto> playerSaveDtos);
}
