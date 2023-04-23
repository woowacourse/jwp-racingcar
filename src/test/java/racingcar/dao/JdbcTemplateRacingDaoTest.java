package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Coin;
import racingcar.domain.DeterminedNumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.persistence.dao.JdbcTemplateRacingDao;
import racingcar.persistence.entity.GameResultEntity;
import racingcar.persistence.entity.PlayerResultEntity;

@JdbcTest
class JdbcTemplateRacingDaoTest {

    private static final int STOP = 3;
    private static final int MOVE = 4;

    private final JdbcTemplateRacingDao jdbcTemplateRacingDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRacingDaoTest(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateRacingDao = new JdbcTemplateRacingDao(jdbcTemplate);
    }

    @Test
    @DisplayName("게임 결과를 저장한다.")
    void shouldSaveGameResultWhenRequest() {
        RacingGame racingGame = new RacingGame(
                Arrays.asList(Car.createBy("브리"), Car.createBy("브라운"), Car.createBy("토미")),
                DeterminedNumberGenerator.createByNumbers(
                        //브리 브라운  토미
                        MOVE, MOVE, STOP,
                        STOP, MOVE, MOVE,
                        MOVE, STOP, STOP
                        // 2     2     1
                ),
                new Coin(3)
        );
        racingGame.play();

        jdbcTemplateRacingDao.saveGameResult(GameResultEntity.createToSave(racingGame, 3));

        String actualWinners = jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
        assertThat(actualWinners).isEqualTo("브리,브라운");
        assertThat(trialCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 별 정보를 저장한다.")
    void shouldSaveEachPlayerResultWhenRequest() {
        // GAME_RESULT 를 저장한다
        RacingGame racingGame = new RacingGame(
                Arrays.asList(Car.createBy("브리"), Car.createBy("브라운"), Car.createBy("토미")),
                DeterminedNumberGenerator.createByNumbers(
                        //브리 브라운  토미
                        MOVE, MOVE, STOP,
                        STOP, MOVE, MOVE,
                        MOVE, STOP, STOP
                        // 2     2     1
                ),
                new Coin(3)
        );
        racingGame.play();
        GameResultEntity gameResultEntity = jdbcTemplateRacingDao.saveGameResult(
                GameResultEntity.createToSave(racingGame, 3)
        );

        // 저장한 GAME_RESULT 을 참조하는 PLAYER_RESULT 를 저장한다
        List<PlayerResultEntity> playerResultEntities = new ArrayList<>();
        for (Car car : racingGame.getCars()) {
            playerResultEntities.add(PlayerResultEntity.createToSave(
                    car,
                    gameResultEntity.getId())
            );
        }
        jdbcTemplateRacingDao.savePlayerResults(playerResultEntities);

        int positionOfBri = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브리'",
                Integer.class);
        int positionOfTomi = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '토미'",
                Integer.class);
        int positionOfBrown = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브라운'",
                Integer.class);
        assertAll(() -> {
            assertThat(positionOfBri).isEqualTo(2);
            assertThat(positionOfBrown).isEqualTo(2);
            assertThat(positionOfTomi).isEqualTo(1);
        });
    }
}
