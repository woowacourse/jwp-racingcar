package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dao.game.GameDao;

@SpringBootTest
class JdbcTemplateGameDaoTest {

    @Autowired
    private GameDao gameDao;

    @DisplayName("게임 정보를 저장하며 id를 반환한다.")
    @Test
    void save() {
        int trialCount = 5;
        int gameId = gameDao.save(trialCount);

        assertThat(gameId).isEqualTo(1);
    }
}
