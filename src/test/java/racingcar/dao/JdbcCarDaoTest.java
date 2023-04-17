package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.GameEntity;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        carDao = new JdbcCarDao(jdbcTemplate);
        gameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    void 게임_id와_함께_차량_정보를_저장한다() {
        // given
        final long firstGameId = gameDao.insert(GameEntity.create(5));
        final long secondGameId = gameDao.insert(GameEntity.create(4));

        final CarEntity firstCarEntity = CarEntity.create("jayon", 3, firstGameId, true);
        final CarEntity secondCarEntity = CarEntity.create("gavi", 2, secondGameId, true);

        List<CarEntity> carEntities = List.of(firstCarEntity, secondCarEntity);

        // expect
        Assertions.assertDoesNotThrow(
                () -> carDao.batchInsert(carEntities)
        );
    }
}
