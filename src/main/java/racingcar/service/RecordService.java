package racingcar.service;

import org.springframework.stereotype.Service;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.model.RecordEntity;
import racingcar.repository.RecordDao;

import java.util.List;

@Service
public class RecordService {

    private final RecordDao recordDao;

    public RecordService(final RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public void save(final long gameId, final Cars cars) {
        String winners = cars.getWinner();

        for (Car car : cars.getCars()) {
            recordDao.insert(gameId, winners.contains(car.getName()), car);
        }
    }

    public List<RecordEntity> findAll() {
        return recordDao.findAll();
    }
}
