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
import racingcar.dao.h2jdbc.JdbcWinnersDao;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestDatabaseConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JdbcWinnersDaoTest {

    @Autowired
    private JdbcGamesDao gamesDao;
    @Autowired
    private JdbcCarsDao carsDao;
    @Autowired
    private JdbcWinnersDao winnersDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Integer gameId;
    private Integer carId;

    @BeforeEach
    void setUp() {
        this.gameId = gamesDao.insert(5);
        this.carId = carsDao.insert(gameId, "자동차", 3);
    }

    @Test
    @DisplayName("우승자 추가 테스트")
    void insert_test() {
        winnersDao.insert(gameId, List.of(carId));

        final String sql = "select car_id from winners where game_id = ?";
        final List<Integer> carIds = jdbcTemplate.queryForList(sql, Integer.class, gameId);

        assertThat(carIds).containsExactlyInAnyOrder(carId);
    }

    @Test
    @DisplayName("우승자 id 조회 테스트")
    void find_winner_ids_test() {
        winnersDao.insert(gameId, List.of(carId));

        final List<String> winnerIds = winnersDao.findAllWinnerNameByGameId(gameId);

        assertThat(winnerIds).containsExactly("자동차");
    }

}
