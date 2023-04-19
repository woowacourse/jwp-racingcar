package racingcar.dao;

import racingcar.dto.PlayerSaveDto;

import java.util.List;

public interface RacingGameDao {

    void save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos);
}
