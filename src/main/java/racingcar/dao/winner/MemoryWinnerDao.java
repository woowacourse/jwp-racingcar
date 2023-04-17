package racingcar.dao.winner;

import java.util.ArrayList;
import java.util.List;

import racingcar.dao.entity.Winner;

public class MemoryWinnerDao implements WinnerDao{
    private final List<Winner> winnerTable = new ArrayList<>();

    @Override
    public void insertWinner(List<Winner> winners) {
        winnerTable.addAll(winners);
    }

    @Override
    public List<Winner> findAllWinner() {
        return List.copyOf(winnerTable);
    }
}
