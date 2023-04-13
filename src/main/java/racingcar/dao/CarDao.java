package racingcar.dao;

public interface CarDao {

    void insert(final String name, final int position, final Long gameId, final boolean isWin);
}
