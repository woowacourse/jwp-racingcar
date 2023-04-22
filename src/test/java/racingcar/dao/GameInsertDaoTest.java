package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.RacingCarApplication;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RacingCarApplication.class)
public class GameInsertDaoTest {

    @Autowired
    private GameInsertDao gameInsertDao;

    @Test
    void insertGame() {
        int gameId1 = gameInsertDao.insertGame(3);
        int gameId2 = gameInsertDao.insertGame(3);
        Assertions.assertThat(gameId2 - gameId1).isEqualTo(1);
    }
}
