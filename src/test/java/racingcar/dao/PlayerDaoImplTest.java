package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
public class PlayerDaoImplTest {

    private PlayerDaoImpl playerDao;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playerDao = new PlayerDaoImpl(namedParameterJdbcTemplate);
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
    }

    @Test
    @DisplayName("saveAllPlayers을 테스트를 진행한다.")
    void saveGame_whenCall_thenSuccess() {
        // given
        final Player kongHana = new Player("콩하나", 10, true);
        final Player ethan = new Player("에단", 5, false);

        // when, then
        assertThatCode(() ->  playerDao.saveAllPlayers(10L, List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }
}
