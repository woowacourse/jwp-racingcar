package racingcar.repository.dao;

public interface PlayRecordsDao {

    void insert(final int count);

    long getLastId();

    void clear();

}
