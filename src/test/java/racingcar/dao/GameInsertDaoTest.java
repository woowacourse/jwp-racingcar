package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
