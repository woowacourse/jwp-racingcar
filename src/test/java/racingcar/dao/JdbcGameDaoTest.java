package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dto.PlayerSaveDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

@JdbcTest
class JdbcGameDaoTest {

    private GameDao gameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        gameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    @DisplayName("Game 테이블을 저장할 수 있다.")
    void saveGame_whenCall_thenSuccess() {
        // given
        // when, then
        assertThatCode(() -> gameDao.save(10))
                .doesNotThrowAnyException();
    }
}
