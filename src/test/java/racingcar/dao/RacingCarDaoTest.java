package racingcar.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.domain.Car;
import racingcar.dto.response.RacingCarResponseDto;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class RacingCarDaoTest {
    @Autowired
    private DataSource dataSource;
    private RacingGameDao racingGameDao;
    private RacingCarDao racingCarDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(dataSource);
        racingCarDao = new RacingCarDao(dataSource);
        gameId = racingGameDao.save("루쿠", 10);
    }

    @DisplayName("자동차 정보를 저장한다.")
    @Test
    void save() {
        assertThat(racingCarDao.save(gameId, new Car("다즐"))).isNotNull();
    }

    @Test
    @DisplayName("모든 자동차 정보를 가져온다.")
    void findAllCars() {
        racingCarDao.save(gameId, new Car("다즐"));
        racingCarDao.save(gameId, new Car("루쿠"));

        List<RacingCarResponseDto> result = racingCarDao.findAllCars();
        assertThat(result.get(0).getName()).isEqualTo("다즐");
        assertThat(result.get(1).getName()).isEqualTo("루쿠");
    }
}
