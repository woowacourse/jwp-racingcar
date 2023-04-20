package racingcar.dao.winner;

import racingcar.dto.WinnerDto;

import java.util.List;

public interface WinnerDao {
    void insert(WinnerDto winnerDto);
    
    List<WinnerDto> findWinnerDtosByGameId(long gameId);
}
