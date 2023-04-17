package racingcar.repository.dao;

import java.util.List;

import racingcar.repository.entity.Record;

public interface FindAllRecordsDao {

    List<Record> findAllRecords();
}
