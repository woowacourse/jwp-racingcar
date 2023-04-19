package racingcar.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.repository.dao.CarsDao;
import racingcar.repository.dao.PlayRecordsDao;
import racingcar.repository.dao.entity.CarEntity;
import racingcar.repository.dao.entity.EntityConverter;

@Transactional(readOnly = true)
@Repository
public class RacingCarRepository {

    private final PlayRecordsDao playRecordsDao;
    private final CarsDao carsDao;

    public RacingCarRepository(final PlayRecordsDao playRecordsDao, final CarsDao carsDao) {
        this.playRecordsDao = playRecordsDao;
        this.carsDao = carsDao;
    }

    @Transactional
    public void save(final int racingCount, final List<Car> cars) {
        playRecordsDao.insert(racingCount);
        long savedId = playRecordsDao.getLastId();
        carsDao.insert(savedId, EntityConverter.toDaoEntities(cars));
    }

    public List<List<Car>> getAll() {
        Map<Long, List<CarEntity>> allCars = carsDao.findAllCarsOrderByPlayCreatedAtDesc();
        return allCars.values()
                .stream()
                .map(EntityConverter::toDomainEntities)
                .collect(Collectors.toList());
    }
}
