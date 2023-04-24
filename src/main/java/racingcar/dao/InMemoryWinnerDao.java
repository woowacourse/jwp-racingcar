package racingcar.dao;

import java.util.List;

public class InMemoryWinnerDao implements WinnerDao {

    private List<String> winners;

    @Override
    public void insert(List<String> winners, long gameId) {
        this.winners = winners;

    }

    @Override
    public List<String> selectByGameId(long gameId) {
        return winners;
    }

}
