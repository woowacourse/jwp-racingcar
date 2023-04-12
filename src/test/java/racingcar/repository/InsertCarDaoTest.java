package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.domain.Car;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class InsertCarDaoTest {

    private InsertCarDao insertCarDao;
    private int gameId;

    @Autowired
    void setUp(final DataSource dataSource) {
        insertCarDao = RepositoryFactory.insertCarDao(dataSource);
        gameId = RepositoryFactory.gamesDao(dataSource).save(10);
    }

    @Test
    void 자동차_저장() {
        final Car car = new Car("토미", 9);

        final CarEntity result = insertCarDao.save(car, gameId);

        assertAll(
                () -> assertThat(result.getId()).isPositive(),
                () -> assertThat(result.getName()).isEqualTo(car.getCarName()),
                () -> assertThat(result.getPosition()).isEqualTo(car.getPosition())
        );
    }
}
