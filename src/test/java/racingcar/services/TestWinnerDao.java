package racingcar.services;

import java.util.List;

import racingcar.dao.winner.WinnerDao;

public class TestWinnerDao implements WinnerDao {
    @Override
    public void insertWinner(String winners, long gameId) {

    }

    @Override
    public List<String> findWinnersByGameId(Long gameId) {
        return List.of("이리내");
    }
}
