package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.PlayRecordEntity;

@JdbcTest
class JdbcCarsDaoTest {

    private final JdbcTemplate jdbcTemplate;
    private JdbcCarsDao carsDao;
    private JdbcPlayRecordsDao playRecordsDao;

    @Autowired
    public JdbcCarsDaoTest(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        carsDao = new JdbcCarsDao(jdbcTemplate);
        playRecordsDao = new JdbcPlayRecordsDao(jdbcTemplate);
    }

    @AfterEach
    void clear() {
        playRecordsDao.clear();
    }

    @DisplayName("DB: 게임 아이디에 따른 자동차 저장 테스트")
    @Test
    void insert() {
        playRecordsDao.insert(new PlayRecordEntity(5));
        long playRecordId = playRecordsDao.getLastId();
        List<CarEntity> cars = createCarEntitiesOf(playRecordId);
        carsDao.insert(cars);

        assertThat(carsDao.find(playRecordId))
                .isEqualTo(cars);
    }

    @DisplayName("DB: 모든 게임 별 자동차 정보 최신순 조회 테스트")
    @Test
    void findAllCarsById() {
        playRecordsDao.insert(new PlayRecordEntity(5));
        List<CarEntity> firstInsertedCars = createCarEntitiesOf(playRecordsDao.getLastId());
        carsDao.insert(firstInsertedCars);
        playRecordsDao.insert(new PlayRecordEntity(5));
        List<CarEntity> secondInsertedCars = createCarEntitiesOf(playRecordsDao.getLastId());
        carsDao.insert(secondInsertedCars);

        List<List<CarEntity>> allCarsByPlayId = carsDao.findAllCarsOrderByPlayCreatedAtDesc();

        assertThat(allCarsByPlayId).containsExactly(firstInsertedCars, secondInsertedCars);
    }

    private static List<CarEntity> createCarEntitiesOf(Long playRecordId) {
        return List.of(
                new CarEntity(playRecordId, "도이", 1),
                new CarEntity(playRecordId, "연어", 3),
                new CarEntity(playRecordId, "브리", 4)
        );
    }

}
