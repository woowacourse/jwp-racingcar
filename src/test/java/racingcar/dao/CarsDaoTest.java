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
import racingcar.dto.JudgedCarDto;

@SpringBootTest
class CarsDaoTest {

    private static final List<JudgedCarDto> FIXTURE_CARS = List.of(
            JudgedCarDto.of("도이", 1, false),
            JudgedCarDto.of("연어", 3, false),
            JudgedCarDto.of("브리", 4, true)
    );

    @Autowired
    private CarsDao carsDao;
    @Autowired
    private PlayRecordsDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao.clear();
    }

    @DisplayName("DB: 게임 아이디에 따른 자동차 저장 테스트")
    @Test
    void insert() {
        long id = gameDao.insertAndReturnId(5);

        carsDao.insert(id, FIXTURE_CARS);

        assertThat(carsDao.find(id))
                .containsExactlyInAnyOrderElementsOf(FIXTURE_CARS);
    }

    @DisplayName("DB: 모든 게임 별 자동차 정보 최신순 조회 테스트")
    @Test
    void findAllCarsById() {
        long id1 = gameDao.insertAndReturnId(5);
        carsDao.insert(id1, FIXTURE_CARS);
        long id2 = gameDao.insertAndReturnId(5);
        carsDao.insert(id2, FIXTURE_CARS);

        Map<Long, List<JudgedCarDto>> allCars = carsDao.findAllCarsByPlayId();

        assertThat(allCars).containsExactly(entry(id2, FIXTURE_CARS), entry(id1, FIXTURE_CARS));
    }

}
