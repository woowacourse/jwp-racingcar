package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

@SpringBootTest
class CarsDaoTest {

    private static final int FIRST_INSERT_ID = 1;
    private static final List<Car> FIXTURE_CARS_ID_NULL = List.of(
            new Car("도이", 1),
            new Car("연어", 3),
            new Car("브리", 4)
    );

    @Autowired
    private CarsDao carsDao;
    @Autowired
    private PlayRecordsDao playRecordsDao;

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
                        new Car("도이", 1),
                        new Car("연어", 3),
                        new Car("브리", 4)
                )
        );
        System.out.println(carsDao.find(FIRST_INSERT_ID));

        assertThat(carsDao.find(FIRST_INSERT_ID))
                .containsExactlyInAnyOrder(
                        new Car(1, "도이", 1),
                        new Car(1, "연어", 3),
                        new Car(1, "브리", 4)
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

        final List<Car> allCars = carsDao.findAllCarsByPlayId();

        assertThat(allCars).containsExactly(
                new Car(id2, "도이", 1),
                new Car(id2, "연어", 3),
                new Car(id2, "브리", 4),
                new Car(id1, "도이", 1),
                new Car(id1, "연어", 3),
                new Car(id1, "브리", 4)
        );
    }

}
