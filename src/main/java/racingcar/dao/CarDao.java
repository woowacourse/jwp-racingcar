package racingcar.dao;

public interface CarDao {

    int insert(final String name, final int position, final Long gameId, final boolean isWin);

    int countRows();

    void deleteAll();
}
