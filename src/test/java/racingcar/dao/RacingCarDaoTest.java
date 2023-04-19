package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import racingcar.domain.Car;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RacingCarDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private RacingCarDao racingCarDao;

    private Long gameId;

    @BeforeEach
    void setUp() {
        gameId = racingGameDao.save("루쿠", 10);
    }

    @DisplayName("자동차 정보를 저장한다.")
    @Test
    void save() {
        assertThat(racingCarDao.save(gameId, new Car("다즐"))).isNotNull();
    }

    @Test
    @DisplayName("자동차들 정보를 가져온다.")
    void find() {
        racingCarDao.save(gameId, new Car("다즐"));

        assertThat(racingCarDao.findCars(gameId).get(0).getName()).isEqualTo("다즐");
    }

}
