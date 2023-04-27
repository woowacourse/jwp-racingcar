package racingcar.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
@DisplayName("CarJdbcDao 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class CarJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameResultDao gameResultDao;

    @BeforeEach
    void setup() {
        carDao = new CarJdbcDao(jdbcTemplate);
        gameResultDao = new GameResultJdbcDao(jdbcTemplate);
    }

    @Test
    void save_test() {
        // given
        final Long gameId = gameResultDao.insert(new GameResultEntity(3));
        final List<CarEntity> carEntities = List.of(new CarEntity("채채", 2, true, gameId));

        // when
        carDao.insert(carEntities);

        // then
        assertThat(carDao.findByGameResultId(gameId)).hasSize(1);
    }

    @Test
    void findAll_test(){
        // given
        final Long gameId = gameResultDao.insert(new GameResultEntity(3));
        final List<CarEntity> cars = List.of(
                new CarEntity("채채", 2, true, gameId),
                new CarEntity("헙크", 2, true, gameId)
        );
        carDao.insert(cars);

        // when
        final List<CarEntity> result = carDao.findByGameResultId(gameId);

        // then
        final CarEntity carEntity = result.get(0);
        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(carEntity.getFinalPosition()).isEqualTo(2),
                () -> assertThat(carEntity.isWinner()).isTrue(),
                () -> assertThat(carEntity.getGameResultId()).isEqualTo(gameId)
        );
    }
}
