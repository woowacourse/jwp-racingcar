package racingcar.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.repository.dao.FindAllRecordsDao;
import racingcar.repository.entity.Record;

@Service
public class FindRecordService {

    private final FindAllRecordsDao findAllRecordsDao;

    public FindRecordService(final FindAllRecordsDao findAllRecordsDao) {
        this.findAllRecordsDao = findAllRecordsDao;
    }

    public List<RacingCarResult> findAllRecords() {
        final List<Record> records = findAllRecordsDao.findAll();
        final Map<Long, List<Record>> recordsByGameId = records.stream()
            .collect(Collectors.groupingBy(Record::getGameId));
        return recordsByGameId.values().stream()
            .map(this::mapToRacingCarResult)
            .collect(Collectors.toList());
    }

    private RacingCarResult mapToRacingCarResult(final List<Record> records) {
        List<String> winners = records.stream()
            .filter(Record::isWinner)
            .map(Record::getName)
            .collect(Collectors.toList());
        List<Car> cars = records.stream()
            .map(record -> Car.of(record.getName(), record.getPosition()))
            .collect(Collectors.toList());
        return new RacingCarResult(winners, cars);
    }
}
