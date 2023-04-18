package racingcar.dao;

import java.util.List;
import racingcar.dto.ResultDto;

public class ConsolePlayerDao implements PlayerDao {
    @Override
    public void insertPlayer(final List<ResultDto> responses, final List<String> winnerNames, final int gameId) {
    }

    @Override
    public List<ResultDto> selectBy(final int gameId) {
        return List.of();
    }

    @Override
    public List<String> selectWinnerBy(final int gameId) {
        return List.of();
    }
}
