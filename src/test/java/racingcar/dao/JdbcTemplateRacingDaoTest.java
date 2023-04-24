package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
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

        this.jdbcTemplateRacingDao.saveGameResult(GameResultEntity.createToSave(racingGame, 3));

        String actualWinners = this.jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = this.jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
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
        GameResultEntity gameResultEntity = this.jdbcTemplateRacingDao.saveGameResult(
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
        this.jdbcTemplateRacingDao.savePlayerResults(playerResultEntities);

        int positionOfBri = this.jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브리'",
                Integer.class);
        int positionOfTomi = this.jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '토미'",
                Integer.class);
        int positionOfBrown = this.jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브라운'",
                Integer.class);
        assertAll(() -> {
            assertThat(positionOfBri).isEqualTo(2);
            assertThat(positionOfBrown).isEqualTo(2);
            assertThat(positionOfTomi).isEqualTo(1);
        });
    }

    @Test
    @DisplayName("모든 게임 결과를 불러온다.")
    void shouldReturnAllGameResultsWhenRequest() {
        // 첫번째 게임 저징
        RacingGame racingGame1 = new RacingGame(
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
        racingGame1.play();
        GameResultEntity gameResultEntity1 = this.jdbcTemplateRacingDao.saveGameResult(
                GameResultEntity.createToSave(racingGame1, 3)
        );

        // 두번째 게임 저장
        RacingGame racingGame2 = new RacingGame(
                Arrays.asList(Car.createBy("브리"), Car.createBy("브라운"), Car.createBy("토미")),
                DeterminedNumberGenerator.createByNumbers(
                        //브리 브라운  토미
                        STOP, STOP, STOP,
                        STOP, STOP, STOP,
                        STOP, STOP, STOP
                        // 0     0     0
                ),
                new Coin(3)
        );
        racingGame1.play();
        GameResultEntity gameResultEntity2 = this.jdbcTemplateRacingDao.saveGameResult(
                GameResultEntity.createToSave(racingGame1, 3)
        );

        List<GameResultEntity> gameResultEntities = this.jdbcTemplateRacingDao.getAllGameResults();

        GameResultEntity gameResultEntity1FromDb = gameResultEntities.get(0);
        GameResultEntity gameResultEntity2FromDb = gameResultEntities.get(1);

        Assertions.assertThat(gameResultEntity1FromDb).isEqualTo(gameResultEntity1);
        Assertions.assertThat(gameResultEntity2FromDb).isEqualTo(gameResultEntity2);
    }

    @DisplayName("모든 플레이어 결과를 불러온다.")
    @Test
    void shouldReturnAllPlayerResultsWhenRequest() {
        GameResultEntity gameResultEntity = this.jdbcTemplateRacingDao.saveGameResult(
                GameResultEntity.createToSave(RacingGame.of(List.of("브리", "브라운", "토미"), 3), 3)
        );
        this.jdbcTemplateRacingDao.saveGameResult(gameResultEntity);

        List<PlayerResultEntity> playerResultEntitiesToSave = List.of(
                PlayerResultEntity.createToSave(new Car("브리", 2), gameResultEntity.getId()),
                PlayerResultEntity.createToSave(new Car("브라운", 2), gameResultEntity.getId()),
                PlayerResultEntity.createToSave(new Car("토미", 1), gameResultEntity.getId())
        );

        this.jdbcTemplateRacingDao.savePlayerResults(playerResultEntitiesToSave);

        List<PlayerResultEntity> playerResultEntitiesFromDb = this.jdbcTemplateRacingDao.getPlayerResultsBy(
                gameResultEntity.getId()
        );

        PlayerResultEntity playerResultEntityOfBrie = playerResultEntitiesFromDb.get(0);
        PlayerResultEntity playerResultEntityOfBrown = playerResultEntitiesFromDb.get(1);
        PlayerResultEntity playerResultEntityOfTommy = playerResultEntitiesFromDb.get(2);

        assertAll(
                () -> assertThat(playerResultEntityOfBrie.getGameResultId()).isEqualTo(gameResultEntity.getId()),
                () -> assertThat(playerResultEntityOfBrown.getName()).isEqualTo("브라운"),
                () -> assertThat(playerResultEntityOfTommy.getPosition()).isEqualTo(1)
        );
    }
}
