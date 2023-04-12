package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RacingGameDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP TABLE PLAYER_RESULT IF EXISTS");
        jdbcTemplate.execute("DROP TABLE GAME_RESULT IF EXISTS");

        jdbcTemplate.execute("CREATE TABLE GAME_RESULT (" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "trial_count INT NOT NULL," +
                "winners VARCHAR(50) NOT NULL," +
                "created_at DATETIME NOT NULL default current_timestamp)");
        jdbcTemplate.execute("CREATE TABLE PLAYER_RESULT (" +
                "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "position INT NOT NULL," +
                "game_result_id INT NOT NULL," +
                "FOREIGN KEY (game_result_id) REFERENCES GAME_RESULT (`id`))");
    }

    @Test
    @DisplayName("게임 결과가 데이터베이스에 저장될 수 있다")
    void shouldSaveGameResultWhenRequest() {
        GameResultDto gameResultDto = new GameResultDto(
                "브리,브라운",
                List.of(
                        new CarDto("브리", "2"),
                        new CarDto("토미", "1"),
                        new CarDto("브라운", "2")));
        racingGameDao.save(gameResultDto, 3);
        String winners = jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
        assertThat(winners).isEqualTo("브리,브라운");
        assertThat(trialCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 별 정보가 데이터베이스에 저장될 수 있다")
    void shouldSaveEachPlayerResultWhenRequest() {
        GameResultDto gameResultDto = new GameResultDto(
                "브리,브라운",
                List.of(
                        new CarDto("브리", "2"),
                        new CarDto("토미", "1"),
                        new CarDto("브라운", "2")));
        racingGameDao.save(gameResultDto, 3);
        int positionOfBri = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브리'", Integer.class);
        int positionOfTomi = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '토미'", Integer.class);
        int positionOfBrown = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브라운'", Integer.class);
        assertAll(() -> {
            assertThat(positionOfBri).isEqualTo(2);
            assertThat(positionOfTomi).isEqualTo(1);
            assertThat(positionOfBrown).isEqualTo(2);
        });
    }
}
