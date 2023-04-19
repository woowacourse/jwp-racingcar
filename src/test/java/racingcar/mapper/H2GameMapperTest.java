package racingcar.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.GameEntity;
import racingcar.mapper.h2.H2GameMapper;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class H2GameMapperTest {

    private final JdbcTemplate jdbcTemplate;
    private H2GameMapper gameMapper;

    @Autowired
    H2GameMapperTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void setUp() {
        this.gameMapper = new H2GameMapper(jdbcTemplate);
    }

    @Test
    void 게임_기록_저장_조회_테스트() {
        GameEntity entity = GameEntity.from(0);

        GameEntity result = gameMapper.save(entity);

        System.out.println(result);
        assertThat(result).isNotNull();
    }
}
