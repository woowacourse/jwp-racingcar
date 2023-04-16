package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerInsertDaoTest {

    @Autowired
    private GameInsertDao jdbcGameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;

    @Test
    void insertPlayers() {
        int gameId = jdbcGameInsertDao.insertGame("jena", 3);
        Assertions.assertThatNoException()
                .isThrownBy(() ->
                        playerInsertDao.insertPlayers(gameId, List.of(new Car("jena", 1), new Car("odo", 2))));
    }
}
