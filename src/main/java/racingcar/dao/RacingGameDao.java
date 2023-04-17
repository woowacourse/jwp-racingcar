package racingcar.dao;

import java.util.List;

public interface RacingGameDao {

    void save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos);
}
