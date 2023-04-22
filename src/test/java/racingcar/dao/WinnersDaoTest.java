package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.entity.GameEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@JdbcTest
public class WinnersDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private WinnersDao winnersDao;
    private RacingCarDao racingCarDao;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        winnersDao = new WinnersDao(jdbcTemplate);
        racingCarDao = new RacingCarDao(jdbcTemplate);
        racingGameDao = new RacingGameDao(jdbcTemplate);
    }

    @Test
    @DisplayName("Game id에 해당하는 우승자(winners) 조회")
    void getWinnerNamesByGameId() {
        GameEntity gameEntity = new GameEntity(2, 10, LocalDateTime.now());
        int gameId = racingGameDao.saveGame(gameEntity);
        racingCarDao.saveCar(gameId, new Car("merry"));
        winnersDao.saveWinners(gameId, racingCarDao.findIdByName("merry"));

        Assertions.assertEquals("merry", winnersDao.getWinnerNamesByGameId(gameId).get(0).getName());
    }

    @Test
    @DisplayName("우승자 저장")
    void saveWinners() {
        int id = 2;
        int count = 10;
        GameEntity gameEntity = new GameEntity(id, count, LocalDateTime.now());
        int gameId = racingGameDao.saveGame(gameEntity);
        racingCarDao.saveCar(gameId, new Car("merry"));
        int carId = racingCarDao.findIdByName("merry");

        assertDoesNotThrow(() -> winnersDao.saveWinners(gameId, carId));
    }

}
