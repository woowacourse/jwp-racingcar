package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.RacingCarApplication;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RacingCarApplication.class)
public class PlayerInsertDaoTest {

    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    @Test
    void insertPlayers() {
        int gameId = gameInsertDao.insertGame(3);
        assertThatCode(() -> playerInsertDao.insertPlayers(
                gameId,
                List.of(new Car("jena", 1), new Car("odo", 2)),
                List.of("odo")
        )).doesNotThrowAnyException();
    }
}
