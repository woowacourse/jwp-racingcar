package racingcar.repository.dao;

import java.util.Collections;
import java.util.List;

import racingcar.repository.entity.Record;

public class ConsoleFindAllRecordDao implements FindAllRecordsDao {

    @Override
    public List<Record> findAllRecords() {
        return Collections.emptyList();
    }
}
