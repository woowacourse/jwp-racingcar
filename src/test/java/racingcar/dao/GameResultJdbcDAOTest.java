package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;
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
        GameResultDto gameResultDto = new GameResultDto(5, "dochi",
                List.of(new CarDto("dochi", 4))
        );

        int id = gameResultJdbcDAO.save(gameResultDto);

        assertThat(id)
                .isEqualTo(1);
    }

    @Test
    void findGame_when_not_exist() {
        List<GameWinnerDto> gameWinners = gameResultJdbcDAO.selectAllGame();
        assertThat(gameWinners).isEmpty();
    }

    @Test
    void findGame_success() {
        gameResultJdbcDAO.save(new GameResultDto(5, "dochi",
                List.of(new CarDto("dochi", 4))
        ));

        List<GameWinnerDto> gameWinners = gameResultJdbcDAO.selectAllGame();
        assertThat(gameWinners.get(0).getWinners()).isEqualTo("dochi");
    }
}
