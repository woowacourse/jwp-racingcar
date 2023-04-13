package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JdbcTemplateDAOTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplateDAO jdbcTemplateDAO;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE GAME_RESULT");
        jdbcTemplate.execute("TRUNCATE TABLE PLAYER_RESULT");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    @DisplayName("게임 결과를 저장할 수 있다.")
    @Test
    void saveGameResultTest() {
        GameResultDto gameResultDto = new GameResultDto(5, "dochi",
                List.of(new CarDto("dochi", 4))
        );

        int id = jdbcTemplateDAO.saveResult(gameResultDto);

        Assertions.assertThat(id)
                .isEqualTo(1);
    }
}
