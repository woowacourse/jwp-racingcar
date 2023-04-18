package racingcar.dao.h2jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.TestDatabaseConfig;
import racingcar.dao.h2jdbc.JdbcCarsDao;
import racingcar.dao.h2jdbc.JdbcGamesDao;
import racingcar.dto.CarDto;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@TestDatabaseConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JdbcCarsDaoTest {

    @Autowired
    private JdbcGamesDao gamesDao;
    @Autowired
    private JdbcCarsDao carsDao;
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

    @Test
    @DisplayName("id로 자동차 조회 테스트")
    void find_by_id_test() {
        final int carId = carsDao.insert(gameId, "car", 5);

        final CarDto carDto = carsDao.findById(carId);

        assertSoftly(softly -> {
            softly.assertThat(carDto.getName()).isEqualTo("car");
            softly.assertThat(carDto.getPosition()).isEqualTo(5);
        });
    }

    @Test
    @DisplayName("gameId로 자동차들 조회 테스트")
    void find_all_by_game_id_test() {
        carsDao.insert(gameId, "car", 5);
        carsDao.insert(gameId, "car2", 2);
        carsDao.insert(gameId, "car3", 4);

        final List<CarDto> carDtos = carsDao.findAllByGameId(gameId);

        assertThat(carDtos).hasSize(3);
    }
}
