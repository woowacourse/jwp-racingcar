package racingcar.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static racingcar.constant.RacingCarDDL.CREATE_TABLE_CAR;
import static racingcar.constant.RacingCarDDL.CREATE_TABLE_GAME;
import static racingcar.constant.RacingCarDDL.DROP_TABLE_CAR;
import static racingcar.constant.RacingCarDDL.DROP_TABLE_GAME;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute(DROP_TABLE_CAR);
        jdbcTemplate.execute(DROP_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_GAME);
        jdbcTemplate.execute(CREATE_TABLE_CAR);
    }

    @Test
    @DisplayName("게임을 저장한다.")
    void insert() {
        int gameId = gameDao.insertGame(5);
        assertThat(gameId).isEqualTo(1);
    }

    @Test
    @DisplayName("모든 게임 id를 조회한다.")
    public void findAllGamesId() {
        int gameId = gameDao.insertGame(5);
        List<Integer> allGamesId = gameDao.findAllGamesId();

        assertThat(allGamesId).containsExactly(gameId);
    }
}
