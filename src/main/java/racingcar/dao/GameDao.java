package racingcar.dao;

import racingcar.dto.ResultDto;

public interface GameDao {
    long saveGame(int trialCount, ResultDto resultDto);
}
