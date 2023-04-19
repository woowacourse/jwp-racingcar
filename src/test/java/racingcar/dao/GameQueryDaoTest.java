package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.RacingCarApplication;
import racingcar.domain.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RacingCarApplication.class)
class GameQueryDaoTest {

    @Autowired
    private GameInsertDao gameInsertDao;
    @Autowired
    private PlayerInsertDao playerInsertDao;
    @Autowired
    private GameQueryDao gameQueryDao;

    @Test
    void getGameIds() {
        int gameId1 = gameInsertDao.insertGame(3);
        int gameId2 = gameInsertDao.insertGame(4);
        int gameId3 = gameInsertDao.insertGame(5);
        List<Integer> gameIds = gameQueryDao.getGameIds();
        assertThat(gameIds).contains(gameId1, gameId2, gameId3);
    }

    @Test
    void getCars() {
        int gameId = gameInsertDao.insertGame(3);
        playerInsertDao.insertPlayers(
                gameId,
                List.of(new Car("jena", 1), new Car("odo", 2)),
                List.of("odo")
        );
        Car jena = gameQueryDao.getCars(gameId).get(0);
        Car odo = gameQueryDao.getCars(gameId).get(1);
        assertThat(jena.getName()).isEqualTo("jena");
        assertThat(jena.getDistance()).isEqualTo(1);
        assertThat(odo.getName()).isEqualTo("odo");
        assertThat(odo.getDistance()).isEqualTo(2);
    }

    @Test
    void getWinners() {
        int gameId = gameInsertDao.insertGame(3);
        playerInsertDao.insertPlayers(
                gameId,
                List.of(new Car("jena", 1), new Car("odo", 1)),
                List.of("jena", "odo")
        );
        assertThat(gameQueryDao.getWinners(gameId)).containsExactly("jena", "odo");
    }
}
