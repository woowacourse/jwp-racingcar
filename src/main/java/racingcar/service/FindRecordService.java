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
        final List<Record> records = findAllRecordsDao.findAllRecords();
        final Map<Long, List<Record>> recordsByGameId = records.stream()
            .collect(Collectors.groupingBy(Record::getGameId));
        return recordsByGameId.values().stream()
            .map(this::mapToRacingCarResult)
            .collect(Collectors.toList());
    }

    private RacingCarResult mapToRacingCarResult(final List<Record> records) {
        final List<String> winners = findWinners(records);
        final List<Car> cars = records.stream()
            .map(record -> Car.of(record.getName(), record.getPosition()))
            .collect(Collectors.toList());
        return new RacingCarResult(winners, cars);
    }

    private List<String> findWinners(final List<Record> records) {
        final int maxPosition = records.stream()
            .mapToInt(Record::getPosition)
            .max()
            .orElseThrow(IllegalStateException::new);

        return records.stream()
            .filter(record -> record.getPosition() == maxPosition)
            .map(Record::getName)
            .collect(Collectors.toList());
    }
}
