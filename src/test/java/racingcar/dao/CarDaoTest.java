package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("DB: 게임 아이디에 따른 자동차 저장 테스트")
    @Test
    void insert() {
        long id = playResultDao.insertAndReturnId(5);

        carDao.insert(id, FIXTURE_CARS);

        assertThat(carDao.find(id))
                .containsExactlyInAnyOrderElementsOf(FIXTURE_CARS);
    }

    @DisplayName("DB: 모든 게임 별 자동차 정보 최신순 조회 테스트")
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
