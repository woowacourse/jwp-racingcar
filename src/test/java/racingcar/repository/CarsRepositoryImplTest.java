package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.service.CarsRepository;
import racingcar.service.GamesRepository;

// @SpringBootTest vs @JdbcTest
@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CarsRepositoryImplTest {

    private final Cars cars = new Cars(List.of("브리", "토미", "브라운"));

    private CarsRepository carsRepository;
    private GamesRepository gamesRepository;
    private int gameId;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        carsRepository = RepositoryFactory.carsRepository(dataSource, jdbcTemplate);
        gamesRepository = RepositoryFactory.gamesRepository(dataSource);
    }

    @BeforeEach
    void setUp() {
        gameId = gamesRepository.save(10);
    }

    @Test
    void 레이스_진행_이후_자동차_저장() {
        carsRepository.save(cars, gameId);

        final List<Car> result = carsRepository.findCars(gameId);
        assertThat(result).hasSize(3);
    }

    @Test
    void 레이스_종료시_승자_저장() {
        carsRepository.save(cars, gameId);

        final List<Car> result = carsRepository.findWinner(gameId);
        assertThat(result).hasSize(3);
    }
}
