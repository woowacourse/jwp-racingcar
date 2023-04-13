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
            racingCarDao.saveGame(generateGameEntity());
        }

        List<GameEntity> result = racingCarDao.findAll();

        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getRacingCars().size(), 2);
        Assertions.assertEquals(result.get(1).getRacingCars().size(), 2);
    }

    public static GameEntity generateGameEntity() {
        return new GameEntity.Builder()
                .count(10)
                .winners("메리")
                .racingCars(new ArrayList<>(List.of(
                                new CarEntity.Builder()
                                        .name("메리")
                                        .position(5)
                                        .build(),
                                new CarEntity.Builder()
                                        .name("매튜")
                                        .position(4)
                                        .build())
                        )
                )
                .build();
    }

}