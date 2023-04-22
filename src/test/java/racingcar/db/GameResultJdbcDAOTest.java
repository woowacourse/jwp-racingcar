package racingcar.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.TryCount;
import racingcar.dto.GameWinnerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameResultJdbcDAOTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private GameResultJdbcDAO gameResultJdbcDAO;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE GAME_RESULT");
        jdbcTemplate.execute("TRUNCATE TABLE RESULT_CAR");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    @DisplayName("게임 결과를 저장할 수 있다.")
    @Test
    void saveGameResultTest() {
        int id = gameResultJdbcDAO.save(new TryCount(5), "dochi", List.of(new Car(new Name("dochi"), 4)));

        assertThat(id)
                .isEqualTo(1);
    }

    @Test
    void findGame_when_not_exist() {
        List<GameWinnerDto> gameWinners = gameResultJdbcDAO.selectAllGameResult();
        assertThat(gameWinners).isEmpty();
    }

    @Test
    void findGame_success() {
        gameResultJdbcDAO.save(new TryCount(5), "dochi", List.of(new Car(new Name("dochi"), 4)));

        List<GameWinnerDto> gameWinners = gameResultJdbcDAO.selectAllGameResult();
        assertThat(gameWinners.get(0))
                .usingRecursiveComparison()
                .isEqualTo(new GameWinnerDto(1, "dochi"));
    }
}
