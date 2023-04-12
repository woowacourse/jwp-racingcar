package racingcar.repository;

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
import racingcar.domain.Car;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CarDaoTest {

    private int gameId;
    private CarDao carDao;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        carDao = RepositoryFactory.carDao(dataSource, jdbcTemplate);
        gameId = RepositoryFactory.gamesDao(dataSource).save(10);
    }

    @Test
    void 자동차_저장() {
        final CarEntity carEntity = carDao.save(new Car("토미", 9), gameId);

        assertAll(
                () -> assertThat(carEntity.getName()).isEqualTo("토미"),
                () -> assertThat(carEntity.getPosition()).isSameAs(9),
                () -> assertThat(carEntity.getId()).isPositive()
        );
    }

    @Nested
    class SelectTest {

        private CarEntity carEntity;

        @BeforeEach
        void setUp() {
            carEntity = carDao.save(new Car("토미", 9), gameId);
        }

        @Test
        void 자동차_전체_조회() {
            final List<CarEntity> result = carDao.findByGameId(gameId);

            assertAll(
                    () -> assertThat(result).hasSize(1),
                    () -> assertThat(result.get(0).getName()).isEqualTo("토미"),
                    () -> assertThat(result.get(0).getPosition()).isEqualTo(9)
            );
        }

        // Entity 동등성 테스트 시 ID 비교 vs 모든 데이터가 동일한지 테스트
        @Test
        void 자동차_단일_조회() {
            final int carId = carEntity.getId();

            final CarEntity result = carDao.findById(carId);

            assertAll(
                    () -> assertThat(result.getId()).isEqualTo(carEntity.getId()),
                    () -> assertThat(result.getName()).isEqualTo(carEntity.getName()),
                    () -> assertThat(result.getPosition()).isEqualTo(carEntity.getPosition())
            );
        }
    }
}
