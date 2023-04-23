package racingcar.dao.winner;

import racingcar.dto.WinnerDto;

import java.util.List;

public interface WinnerDao {
    void save(final WinnerDto winnerDto);
    
    List<WinnerDto> findWinnerDtosByGameId(final long gameId);
}
