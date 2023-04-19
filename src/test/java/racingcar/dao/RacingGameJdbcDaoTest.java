package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
class RacingGameJdbcDaoTest {

    private RacingGameDao racingGameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameJdbcDao(jdbcTemplate);
    }

    @Test
    @DisplayName("saveGame을 테스트를 진행한다.")
    void saveGame_whenCall_thenSuccess() {
        // when
        final long saveId = racingGameDao.save(10);

        // then
        assertThat(saveId).isEqualTo(1);
    }
}
