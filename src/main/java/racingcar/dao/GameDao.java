package racingcar.dao;

public interface GameDao {
    Long insert(final int count);

    int countRows();

    void deleteAll();
}
