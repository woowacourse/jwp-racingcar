package racing.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racing.controller.dto.request.CarRequest;
import racing.domain.Car;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new RacingGameDao(jdbcTemplate);
    }

    @DisplayName("게임을 저장한다.")
    @Test
    void saveGame() {
        // given
        final int count = 10;
        final String query = "select count(*) from games";

        // when
        gameDao.saveGame(count);
        Integer resultCount = jdbcTemplate.queryForObject(query, Integer.class);

        // then
        Assertions.assertThat(resultCount).isEqualTo(1);
    }

    @DisplayName("차를 저장한다.")
    @Test
    void saveCar() {
        // given
        CarRequest request = CarRequest.of(1L, new Car("bebe"), true);
        final String query = "select count(*) from cars";

        // when
        gameDao.saveCar(request);
        Integer resultCount = jdbcTemplate.queryForObject(query, Integer.class);

        // then
        Assertions.assertThat(resultCount).isEqualTo(1);
    }

}
