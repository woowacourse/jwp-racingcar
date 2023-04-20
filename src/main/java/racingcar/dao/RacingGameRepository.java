package racingcar.dao;

import racingcar.dto.PlayerSaveDto;

import java.util.List;

public interface RacingGameRepository {

    void save(final int trialCount, final List<PlayerSaveDto> playerSaveDtos);
}
