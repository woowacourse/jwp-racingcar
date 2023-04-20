package racing.persistence.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import racing.persistence.h2.game.H2RacingGameDao;
import racing.persistence.h2.game.RacingGameDao;
import racing.persistence.h2.game.RacingGameEntity;

@TestInstance(Lifecycle.PER_CLASS)
@Import(H2RacingGameDao.class)
@JdbcTest
class H2GameDaoTest {

    @Autowired
    private RacingGameDao racingGameDao;

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
    void findAllGameByRecentTest() throws InterruptedException {
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
