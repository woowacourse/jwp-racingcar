package racingcar.repository.dao;

import racingcar.repository.dao.entity.PlayRecordEntity;

public interface PlayRecordsDao {

    void insert(final PlayRecordEntity playRecord);

    long getLastId();

    void clear();

}
