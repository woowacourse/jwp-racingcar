package racingcar.dao;

import racingcar.dto.ResultDto;

public interface WinnerDao {
    void insertWinner(ResultDto resultDto, long gameId);
}
