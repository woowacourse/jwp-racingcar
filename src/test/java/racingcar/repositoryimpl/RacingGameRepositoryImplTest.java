package racingcar.repositoryimpl;

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
import racingcar.dao.CarDao;
import racingcar.dao.GamesDao;
import racingcar.dao.WinnerDao;
import racingcar.domain.Car;
import racingcar.domain.Count;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RacingGameRepository;
import racingcar.repository.RepositoryFactory;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class RacingGameRepositoryImplTest {

    private RacingGameRepository racingGameRepository;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        final GamesDao gamesDao = RepositoryFactory.gamesDao(dataSource, jdbcTemplate);
        final CarDao carDao = RepositoryFactory.carDao(dataSource, jdbcTemplate);
        final WinnerDao winnerDao = RepositoryFactory.winnerDao(dataSource, jdbcTemplate);

        racingGameRepository = new RacingGameRepositoryImpl(gamesDao, carDao, winnerDao);
    }

    @Test
    void 게임_저장() {
        final List<Car> cars = List.of(new Car("브리", 9));
        final RacingGameDto racingGameDto = new RacingGameDto(cars, cars, new Count(10));

        final RacingGameDto result = racingGameRepository.save(racingGameDto);

        assertAll(
                () -> assertThat(result.getTotalCars()).hasSize(1),
                () -> assertThat(result.getWinners()).hasSize(1),
                () -> assertThat(result.getCount().getTargetCount()).isEqualTo(10)
        );
    }

    @Nested
    class FindAllTest {

        @BeforeEach
        void setUp() {
            final List<Car> cars = List.of(new Car("브리", 9));
            final RacingGameDto racingGameDto = new RacingGameDto(cars, cars, new Count(10));

            racingGameRepository.save(racingGameDto);
        }

        @Test
        void 게임_전체_이력_조회() {
            final List<RacingGameDto> result = racingGameRepository.findAll();

            assertThat(result).hasSizeGreaterThan(0);
        }
    }
}
