package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.web.RacingCarWebDao;
import racingcar.dao.web.RacingGameWebDao;
import racingcar.dao.web.WinnersWebDao;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@JdbcTest
public class WinnersWebDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private WinnersWebDao winnersWebDao;
    private RacingCarWebDao racingCarWebDao;
    private RacingGameWebDao racingGameWebDao;

    @BeforeEach
    void setUp() {
        winnersWebDao = new WinnersWebDao(jdbcTemplate);
        racingCarWebDao = new RacingCarWebDao(jdbcTemplate);
        racingGameWebDao = new RacingGameWebDao(jdbcTemplate);
    }

    @Test
    @DisplayName("Game id에 해당하는 우승자(winners) 조회")
    void getWinnerNamesByGameId() {
        GameEntity gameEntity = new GameEntity(2, 10, LocalDateTime.now());
        int gameId = racingGameWebDao.saveGame(gameEntity);
        racingCarWebDao.saveCar(new CarEntity(1, "merry", 1, gameId));
        winnersWebDao.saveWinners(gameId, racingCarWebDao.findIdByName("merry"));

        Assertions.assertEquals("merry", winnersWebDao.getWinnerNamesByGameId(gameId).get(0).getName());
    }

    @Test
    @DisplayName("우승자 저장")
    void saveWinners() {
        int id = 3;
        int count = 10;
        GameEntity gameEntity = new GameEntity(id, count, LocalDateTime.now());
        int gameId = racingGameWebDao.saveGame(gameEntity);
        racingCarWebDao.saveCar(new CarEntity(1, "merry", 1, gameId));
        int carId = racingCarWebDao.findIdByName("merry");

        assertDoesNotThrow(() -> winnersWebDao.saveWinners(gameId, carId));
    }

}
