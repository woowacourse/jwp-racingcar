package racingcar.dao;

import racingcar.dao.entity.PlayRecordEntity;

public interface PlayRecordsDao {

    void insert(final PlayRecordEntity playRecord);

    long getLastId();

    void clear();

}
