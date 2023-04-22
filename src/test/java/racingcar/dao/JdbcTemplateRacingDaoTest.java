package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;
import racingcar.dto.RacingResultDto;

@JdbcTest
class JdbcTemplateRacingDaoTest {

    private final JdbcTemplateRacingDao jdbcTemplateRacingDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRacingDaoTest(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateRacingDao = new JdbcTemplateRacingDao(jdbcTemplate);
    }

    @Test
    @DisplayName("게임 결과가 데이터베이스에 저장될 수 있다")
    void shouldSaveGameResultWhenRequest() {

        RacingResultDto racingResultDto = new RacingResultDto(
                List.of(new CarDto("브리", 2), new CarDto("브라운", 1)),
                List.of(new CarDto("브리", 2), new CarDto("브라운", 1), new CarDto("토미", 0))
        );
        jdbcTemplateRacingDao.saveGameResult(racingResultDto, 3);

        String actualWinners = jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
        assertThat(actualWinners).isEqualTo("브리,브라운");
        assertThat(trialCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 별 정보가 데이터베이스에 저장될 수 있다")
    void shouldSaveEachPlayerResultWhenRequest() {
        // GAME_RESULT 를 저장한다
        List<CarDto> winnersCarDtos = List.of(
                new CarDto("브리", 2),
                new CarDto("브라운", 2)
        );
        List<CarDto> carDtos = List.of(
                new CarDto("브리", 2),
                new CarDto("토미", 1),
                new CarDto("브라운", 2)
        );
        RacingResultDto racingResultDto = new RacingResultDto(winnersCarDtos, carDtos);
        int gameResultKey = jdbcTemplateRacingDao.saveGameResult(racingResultDto, 3);

        // 저장한 GAME_RESULT 을 참조하는 PLAYER_RESULT 를 저장한다
        jdbcTemplateRacingDao.savePlayerResults(carDtos, gameResultKey);

        int positionOfBri = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브리'",
                Integer.class);
        int positionOfTomi = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '토미'",
                Integer.class);
        int positionOfBrown = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브라운'",
                Integer.class);
        assertAll(() -> {
            assertThat(positionOfBri).isEqualTo(2);
            assertThat(positionOfTomi).isEqualTo(1);
            assertThat(positionOfBrown).isEqualTo(2);
        });
    }
}
