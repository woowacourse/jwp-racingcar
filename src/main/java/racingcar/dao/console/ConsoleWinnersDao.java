package racingcar.dao.console;

import racingcar.dao.WinnersDao;

import java.util.List;

public class ConsoleWinnersDao implements WinnersDao {



    @Override
    public void insert(final int gameId, final List<Integer> winnerIds) {

    }

    @Override
    public List<String> findAllWinnerNameByGameId(final int gameId) {
        return null;
    }
}
