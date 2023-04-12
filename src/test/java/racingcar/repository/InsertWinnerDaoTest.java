package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

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
class InsertWinnerDaoTest {

    private InsertWinnerDao insertWinnerDao;
    private int carId;
    private int gameId;

    @Autowired
    void setUp(final DataSource dataSource) {
        insertWinnerDao = RepositoryFactory.insertWinnerDao(dataSource);
        gameId = RepositoryFactory.gamesDao(dataSource).save(10);
        carId = RepositoryFactory.insertCarDao(dataSource).save(new Car("토미", 9), gameId).getId();
    }

    @Test
    void 자동차_저장() {
        final int result = insertWinnerDao.save(gameId, carId);

        assertThat(result).isPositive();
    }
}
