package racingcar.dao.winner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import racingcar.util.ValueEditor;

public class MemoryWinnerDao implements WinnerDao{
    private final Map<Long, String> winnerTable = new HashMap<>();

    @Override
    public void insertWinner(String winners, long gameId) {
        winnerTable.put(gameId, winners);
    }

    @Override
    public List<String> findWinnersByGameId(Long gameId) {
        return ValueEditor.splitByComma(winnerTable.get(gameId));
    }
}
