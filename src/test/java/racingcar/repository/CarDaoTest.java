package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.RacingGame;
import racingcar.repository.entity.CarEntity;
import racingcar.repository.entity.GameEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CarDaoTest {

    private int gameId;
    private CarDao carDao;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        final GameEntity gameEntity = new GameEntity(null, new RacingGame(List.of("브리"), 5));

        gameId = RepositoryFactory.gamesDao(dataSource).save(gameEntity).getGameId();
        carDao = RepositoryFactory.carDao(dataSource, jdbcTemplate);
    }

    @Test
    void 자동차_전체_저장() {
        final List<CarEntity> result = carDao.insertAll(List.of(new CarEntity("토미", 9)), gameId);

        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getName()).isEqualTo("토미"),
                () -> assertThat(result.get(0).getPosition()).isSameAs(9),
                () -> assertThat(result.get(0).getId()).isPositive()
        );
    }
}
