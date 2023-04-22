package racingcar.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import racingcar.domain.Car;
import racingcar.domain.dto.RacingCarResult;
import racingcar.repository.dao.FindAllRecordsDao;
import racingcar.repository.entity.Record;

@Service
public class FindRacingCarResultService {

    private final FindAllRecordsDao findAllRecordsDao;

    public FindRacingCarResultService(final FindAllRecordsDao findAllRecordsDao) {
        this.findAllRecordsDao = findAllRecordsDao;
    }

    public List<RacingCarResult> findAll() {
        final List<Record> records = findAllRecordsDao.findAll();
        final Map<Long, List<Record>> recordsByGameId = records.stream()
            .collect(groupingBy(Record::getGameId));
        return recordsByGameId.values().stream()
            .map(this::toRacingCarResult)
            .collect(toList());
    }

    private RacingCarResult toRacingCarResult(final List<Record> records) {
        final List<String> winners = records.stream()
            .filter(Record::isWinner)
            .map(Record::getName)
            .collect(toList());
        final List<Car> cars = records.stream()
            .map(record -> Car.of(record.getName(), record.getPosition()))
            .collect(toList());
        return new RacingCarResult(winners, cars);
    }
}
