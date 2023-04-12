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
        jdbcTemplate.execute("DELETE FROM GAME_RESULT");
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
