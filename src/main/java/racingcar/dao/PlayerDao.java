package racingcar.dao;

import java.util.List;
import racingcar.dto.ResultDto;

public interface PlayerDao {

    public void insertPlayer(List<ResultDto> responses, List<String> winnerNames, int gameId);

    public List<ResultDto> selectBy(final int gameId);

    public List<String> selectWinnerBy(final int gameId);
}
