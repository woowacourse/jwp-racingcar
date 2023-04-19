package racingcar.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.GamesDao;
import racingcar.dao.entity.InsertGameEntity;
import racingcar.dao.entity.SelectGameEntity;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RepositoryFactory;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class GamesDaoTest {

    private GamesDao gamesDao;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        gamesDao = RepositoryFactory.gamesDao(dataSource, jdbcTemplate);
    }

    @Test
    void 저장시_게임_id_가_반환된다() {
        final List<Car> cars = List.of(new Car("브리", 3));
        final RacingGameDto racingGameDto = new RacingGameDto(cars, cars, new Count(5));
        final InsertGameEntity insertGameEntity = new InsertGameEntity(null, racingGameDto);
        final InsertGameEntity result = gamesDao.insert(insertGameEntity);

        assertThat(result.getGameId()).isPositive();
    }

    @Nested
    class SelectGamesTest {

        @BeforeEach
        void setUp() {
            final List<Car> cars = List.of(new Car("브리", 3));
            final RacingGameDto racingGameDto = new RacingGameDto(cars, cars, new Count(5));
            final InsertGameEntity insertGameEntity = new InsertGameEntity(null, racingGameDto);

            gamesDao.insert(insertGameEntity);
            gamesDao.insert(insertGameEntity);
        }

        @Test
        void 조회시_모든_게임이_반환된다() {
            final List<SelectGameEntity> result = gamesDao.findAll();

            assertAll(
                    () -> assertThat(result).hasSizeGreaterThanOrEqualTo(2),
                    () -> assertThat(result.get(0).getGameId()).isPositive(),
                    () -> assertThat(result.get(1).getGameId()).isPositive()
            );
        }
    }
}
