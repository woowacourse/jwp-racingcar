package racingcar.dao;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerInsertDaoTest {

    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    @Test
    void insertPlayers() {
        int gameId = gameInsertDao.insertGame("jena", 3);
        Assertions.assertThatNoException()
                .isThrownBy(() ->
                        playerInsertDao.insertPlayers(gameId, List.of(new Car("jena", 1), new Car("odo", 2))));
    }
}
