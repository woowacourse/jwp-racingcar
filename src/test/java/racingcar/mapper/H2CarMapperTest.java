package racingcar.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;
import racingcar.mapper.h2.H2CarMapper;
import racingcar.mapper.h2.H2GameMapper;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class H2CarMapperTest {

    private final JdbcTemplate jdbcTemplate;
    private H2GameMapper h2GameMapper;
    private H2CarMapper h2CarMapper;

    @Autowired
    H2CarMapperTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        this.h2GameMapper = new H2GameMapper(jdbcTemplate);
        this.h2CarMapper = new H2CarMapper(jdbcTemplate);
    }

    @Test
    void 자동차_기록_저장_조회_테스트() {
        GameEntity gameEntity = GameEntity.from(10);
        CarEntity carEntity = CarEntity.of(h2GameMapper.save(gameEntity), "name", 0);

        CarEntity result = h2CarMapper.save(carEntity);

        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
