package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.repository.dao.JdbcCarsDao;
import racingcar.repository.dao.JdbcPlayRecordsDao;
import racingcar.repository.dao.entity.CarEntity;

@SpringBootTest
class JdbcCarsDaoTest {

    private static final int FIRST_INSERT_ID = 1;
    private static final List<CarEntity> FIXTURE_CARS_ID_NULL = List.of(
            new CarEntity("도이", 1),
            new CarEntity("연어", 3),
            new CarEntity("브리", 4)
    );

    @Autowired
    private JdbcCarsDao carsDao;
    @Autowired
    private JdbcPlayRecordsDao playRecordsDao;

    @BeforeEach
    void setUp() {
        playRecordsDao.insert(5);
    }

    @AfterEach
    void clear() {
        playRecordsDao.clear();
    }

    @DisplayName("DB: 게임 아이디에 따른 자동차 저장 테스트")
    @Test
    void insert() {
        carsDao.insert(1, List.of(
                        new CarEntity("도이", 1),
                        new CarEntity("연어", 3),
                        new CarEntity("브리", 4)
                )
        );
        System.out.println(carsDao.find(FIRST_INSERT_ID));

        assertThat(carsDao.find(FIRST_INSERT_ID))
                .containsExactlyInAnyOrder(
                        new CarEntity(1L, "도이", 1),
                        new CarEntity(1L, "연어", 3),
                        new CarEntity(1L, "브리", 4)
                );
    }

    @DisplayName("DB: 모든 게임 별 자동차 정보 최신순 조회 테스트")
    @Test
    void findAllCarsById() {
        long id1 = playRecordsDao.getLastId();
        carsDao.insert(id1, FIXTURE_CARS_ID_NULL);
        playRecordsDao.insert(5);
        long id2 = playRecordsDao.getLastId();
        carsDao.insert(id2, FIXTURE_CARS_ID_NULL);

        Map<Long, List<CarEntity>> allCarsByPlayId = carsDao.findAllCarsOrderByPlayCreatedAtDesc();

        assertThat(allCarsByPlayId).containsExactly(
                entry(id2, List.of(
                        new CarEntity(id2, "도이", 1),
                        new CarEntity(id2, "연어", 3),
                        new CarEntity(id2, "브리", 4)
                )),
                entry(id1, List.of(
                        new CarEntity(id1, "도이", 1),
                        new CarEntity(id1, "연어", 3),
                        new CarEntity(id1, "브리", 4)
                ))
        );
    }

}
