package racingcar.domain.dao;

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
import racingcar.dao.WinnerDao;
import racingcar.dao.entity.CarEntity;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.dao.entity.WinnerEntity;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RepositoryFactory;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WinnerDaoTest {

    private WinnerDao winnerDao;
    private CarEntity carEntity;
    private int gameId;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        final List<Car> cars = List.of(new Car("브리", 3));
        final RacingGameDto racingGameDto = new RacingGameDto(cars, cars, new Count(5));
        final InsertGameEntity insertGameEntity = new InsertGameEntity(null, racingGameDto);

        gameId = RepositoryFactory.gamesDao(dataSource, jdbcTemplate).insert(insertGameEntity).getGameId();
        carEntity = RepositoryFactory.carDao(dataSource, jdbcTemplate)
                .insertAll(List.of(new CarEntity("브리", 9)), gameId).get(0);
        winnerDao = RepositoryFactory.winnerDao(dataSource, jdbcTemplate);
    }

    @Test
    void 우승자_저장() {
        final List<WinnerEntity> result = winnerDao.saveAll(List.of(carEntity), gameId);

        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.get(0).getGameId()).isPositive()
        );
    }
}
