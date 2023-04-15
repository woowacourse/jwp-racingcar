package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.CarDto;

@SpringBootTest
class CarDaoTest {

    private static final List<CarDto> FIXTURE_CARS = List.of(
            CarDto.of("도이", 1, false),
            CarDto.of("연어", 3, false),
            CarDto.of("브리", 4, true)
    );

    @Autowired
    private CarDao carDao;
    @Autowired
    private PlayResultDao playResultDao;

    @BeforeEach
    void setUp() {
        playResultDao.clear();
    }

    @Test
    void insert() {
        long id = playResultDao.insertAndReturnId(5);

        carDao.insert(id, FIXTURE_CARS);

        assertThat(carDao.find(id))
                .containsExactlyInAnyOrderElementsOf(FIXTURE_CARS);
    }

    // TODO 모든 테스트 displayname 작성
    @Test
    void findAllCarsById() {
        long id1 = playResultDao.insertAndReturnId(5);
        carDao.insert(id1, FIXTURE_CARS);
        long id2 = playResultDao.insertAndReturnId(5);
        carDao.insert(id2, FIXTURE_CARS);

        Map<Long, List<CarDto>> allCars = carDao.findAllCarsById();

        assertThat(allCars).containsExactly(entry(id2, FIXTURE_CARS), entry(id1, FIXTURE_CARS));
    }

}
