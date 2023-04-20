package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

@JdbcTest
@DisplayName("CarJdbcDao 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CarJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        carDao = new CarJdbcDao(jdbcTemplate);
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @Test
    void saveAll_메서드는_입력받은_차를_전부_저장한다() {
        // given
        final Integer gameId = gameDao.saveAndGetId(new GameEntity(3)).get();
        final List<CarEntity> cars = List.of(new CarEntity("car1", 1, true, gameId));

        // when
        carDao.saveAll(cars);

        // then
        final List<CarEntity> result = carDao.findAll();
        assertThat(result).hasSize(1);
    }

    @Test
    void findAll_메서드는_모든_차를_반환한다() {
        // given
        final Integer gameId = gameDao.saveAndGetId(new GameEntity(3)).get();
        final List<CarEntity> cars = List.of(
                new CarEntity("car1", 1, false, gameId),
                new CarEntity("car2", 3, true, gameId)
        );
        carDao.saveAll(cars);

        // when
        final List<CarEntity> result = carDao.findAll();

        // then
        final CarEntity carEntity = result.get(0);
        assertAll(
                () -> assertThat(result).hasSize(2),
                () -> assertThat(carEntity.getId()).isPositive(),
                () -> assertThat(carEntity.getName()).isEqualTo("car1"),
                () -> assertThat(carEntity.getPosition()).isEqualTo(1),
                () -> assertThat(carEntity.isWinner()).isFalse(),
                () -> assertThat(carEntity.getGameId()).isEqualTo(gameId)
        );
    }
}
