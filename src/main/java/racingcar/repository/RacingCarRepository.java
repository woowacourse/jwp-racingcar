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
import racingcar.repository.dao.entity.PlayRecordEntity;

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
        // TODO PlayRecord 도 insert 하는 것 적절할까? => 이 부분은 유지하되 Repository 가 Dao를 통합해 도메인 객체를 도출하는 계층이 되도록 하기
        playRecordsDao.insert(
                PlayRecordEntity.builder()
                        .count(racingCount)
                        .build()
        );
        long savedId = playRecordsDao.getLastId();
        // TODO playId를 CarEntity가 필드로 가지는 의미가 없다.
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
