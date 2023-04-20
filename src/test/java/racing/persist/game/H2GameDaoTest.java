package racing.persist.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

@TestInstance(Lifecycle.PER_CLASS)
@Import(H2RacingGameDao.class)
@JdbcTest
class H2GameDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    void setUpSchema() {
        createCarTable();
        createGameTable();
    }

    private void createGameTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS games (" +
                "game_id bigint NOT NULL AUTO_INCREMENT, " +
                "count int NOT NULL," +
                "create_time timestamp NOT NULL," +
                "PRIMARY KEY (game_id)" +
                ");");
    }

    private void createCarTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS cars (" +
                "car_id bigint NOT NULL AUTO_INCREMENT," +
                "car_name VARCHAR(8) NOT NULL," +
                "step int NOT NULL," +
                "winner boolean default false," +
                "game_id bigint not null," +
                "PRIMARY KEY (car_id)" +
                ");");
    }

    @DisplayName("시도 횟수를 통해 새로운 게임을 생성할 수 있다")
    @Test
    void saveGameTest() {
        int gameTrialCount = 4;
        Long gameId = racingGameDao.saveGame(gameTrialCount);

        RacingGameEntity game = racingGameDao.findGameById(gameId);

        assertThat(gameId).isEqualTo(game.getGameId());
        assertThat(game.getTrialCount()).isEqualTo(gameTrialCount);
    }

    @DisplayName("저장시간을 기준으로 최신순으로 게임 Id 조회")
    @Test
    public void findAllGameByRecentTest() throws InterruptedException {
        int gameAmount = 3;
        for (int i = 0; i < gameAmount; i++) {
            Thread.sleep(100); // 동시간대에 저장되어 TIMESTAMP에 동일한 값이 들어가는 경우를 방지
            racingGameDao.saveGame(1);
        }

        List<RacingGameEntity> allGame = racingGameDao.findAllGameByRecent();
        assertThat(allGame).hasSize(gameAmount);

        List<Long> allGameIds = allGame.stream()
                .map(RacingGameEntity::getGameId)
                .collect(Collectors.toList());
        assertThat(allGameIds)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}
