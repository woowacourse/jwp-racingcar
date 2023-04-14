package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.TestDatabaseConfig;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestDatabaseConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarsDaoTest {

    @Autowired
    private GamesDao gamesDao;
    @Autowired
    private CarsDao carsDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Integer gameId;

    @BeforeEach
    void setUp() {
        this.gameId = gamesDao.insert(5);
    }

    @Test
    @DisplayName("자동차 추가 테스트")
    void insert_test() {
        final int carId = carsDao.insert(gameId, "자동차", 3);

        final String sql = "select * from cars where id=?";
        final Map<String, Object> row = jdbcTemplate.queryForMap(sql, carId);

        assertThat(row).containsAllEntriesOf(Map.of(
                "name", "자동차",
                "position", 3
        ));
    }

}
