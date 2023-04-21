package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.PlayRecordEntity;

@SpringBootTest
class JdbcCarsDaoTest {

    private static final Long FIRST_INSERT_ID = 1L;

    @Autowired
    private JdbcCarsDao carsDao;
    @Autowired
    private JdbcPlayRecordsDao playRecordsDao;

    @BeforeEach
    void setUp() {
        playRecordsDao.insert(new PlayRecordEntity(5));
    }

    @AfterEach
    void clear() {
        playRecordsDao.clear();
    }

    @DisplayName("DB: 게임 아이디에 따른 자동차 저장 테스트")
    @Test
    void insert() {
        List<CarEntity> cars = createCarEntitiesOf(FIRST_INSERT_ID);
        carsDao.insert(cars);

        assertThat(carsDao.find(FIRST_INSERT_ID))
                .isEqualTo(cars);
    }

    @DisplayName("DB: 모든 게임 별 자동차 정보 최신순 조회 테스트")
    @Test
    void findAllCarsById() {
        List<CarEntity> firstInsertedCars = createCarEntitiesOf(playRecordsDao.getLastId());
        carsDao.insert(firstInsertedCars);
        playRecordsDao.insert(new PlayRecordEntity(5));

        List<CarEntity> secondInsertedCars = createCarEntitiesOf(playRecordsDao.getLastId());
        carsDao.insert(secondInsertedCars);

        List<List<CarEntity>> allCarsByPlayId = carsDao.findAllCarsOrderByPlayCreatedAtDesc();

        assertThat(allCarsByPlayId).containsExactly(secondInsertedCars, firstInsertedCars);
    }

    private static List<CarEntity> createCarEntitiesOf(Long playRecordId) {
        return List.of(
                new CarEntity(playRecordId, "도이", 1),
                new CarEntity(playRecordId, "연어", 3),
                new CarEntity(playRecordId, "브리", 4)
        );
    }

}
