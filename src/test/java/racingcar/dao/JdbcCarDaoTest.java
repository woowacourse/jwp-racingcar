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
import racingcar.domain.car.Car;
import racingcar.dto.CarDTO;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameDao gameDao;

    private long gameId;

    @BeforeEach
    void setUp() {
        carDao = new JdbcCarDao(jdbcTemplate);
        gameDao = new JdbcGameDao(jdbcTemplate);
        gameId = gameDao.insert(GameEntity.create(5));
    }

    @Test
    void 게임_id와_함께_차량_정보를_저장한다() {
        // given
        final CarEntity firstCarEntity = CarEntity.create("jayon", 3, gameId, true);
        final CarEntity secondCarEntity = CarEntity.create("gavi", 2, gameId, true);

        List<CarEntity> carEntities = List.of(firstCarEntity, secondCarEntity);

        // expect
        Assertions.assertDoesNotThrow(
                () -> carDao.batchInsert(carEntities)
        );
    }


    @Test
    void 한_경주에_참여한_모든_자동차를_반환한다() {
        // given
        carDao.batchInsert(List.of(
                CarEntity.create("jayon", 8, gameId, true),
                CarEntity.create("gavi", 6, gameId, false),
                CarEntity.create("huchu", 4, gameId, true)
        ));

        // when
        List<Car> cars = carDao.selectAll((int) gameId);

        // then
        assertThat(cars).hasSize(3);
    }

    @Test
    void 우승자를_반환한다() {
        // given
        carDao.batchInsert(List.of(
                CarEntity.create("제이온", 8, gameId, true),
                CarEntity.create("가비", 6, gameId, false),
                CarEntity.create("후추", 4, gameId, true)
        ));

        // when
        List<String> winners = carDao.selectWinners((int) gameId);

        // then
        assertThat(winners).hasSize(2);
    }
}
