package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

@SpringBootTest
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

}
