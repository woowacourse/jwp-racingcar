package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarDao racingCarDao;

    @BeforeEach
    void setUp() {
        racingCarDao = new RacingCarDao(jdbcTemplate);
    }

    @Test
    void saveGame() {
        GameEntity gameEntity = generateGameEntity();

        Assertions.assertDoesNotThrow(() -> racingCarDao.saveGame(gameEntity));
    }

    @Test
    void findAll() {
        for (int i = 0; i < 2; i++) {
            GameEntity gameEntity = racingCarDao.saveGame(generateGameEntity());
            generateCarEntity(gameEntity.getId()).forEach(racingCarDao::saveCar);
        }

        List<GameEntity> resultGame = racingCarDao.findAllGame();
        List<List<CarEntity>> resultCar = resultGame.stream()
                .map(gameEntity -> racingCarDao.findCarsByGameId(gameEntity.getId()))
                .collect(Collectors.toList());

        Assertions.assertEquals(resultGame.size(), 2);
        Assertions.assertEquals(resultCar.get(0).size(), 2);
        Assertions.assertEquals(resultCar.get(1).size(), 2);
    }

    public static GameEntity generateGameEntity() {
        return new GameEntity.Builder()
                .count(10)
                .winners("메리")
                .build();
    }

    public static List<CarEntity> generateCarEntity(int gameId) {
        return List.of(
                new CarEntity.Builder()
                        .name("메리")
                        .position(5)
                        .gameId(gameId)
                        .build(),
                new CarEntity.Builder()
                        .name("매튜")
                        .position(4)
                        .gameId(gameId)
                        .build());
    }

}
