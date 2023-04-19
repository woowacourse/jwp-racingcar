package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.repository.h2.H2CarRepository;
import racingcar.repository.h2.H2GameRepository;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class H2CarRepositoryTest {

    private final JdbcTemplate jdbcTemplate;
    private H2GameRepository h2GameMapper;
    private H2CarRepository h2CarMapper;

    @Autowired
    H2CarRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        this.h2GameMapper = new H2GameRepository(jdbcTemplate);
        this.h2CarMapper = new H2CarRepository(jdbcTemplate);
    }

    @Test
    void 자동차_기록_저장_조회_테스트() {
        GameEntity gameEntity = GameEntity.from(10);
        CarEntity carEntity = CarEntity.of(h2GameMapper.save(gameEntity).getId(), "name", 0);

        CarEntity result = h2CarMapper.save(carEntity);

        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
