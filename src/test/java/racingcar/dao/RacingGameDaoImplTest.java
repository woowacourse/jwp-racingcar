package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
class RacingGameDaoImplTest {

    private RacingGameDao racingGameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDaoImpl(jdbcTemplate);
    }

    @Test
    @DisplayName("saveGame을 테스트를 진행한다.")
    void saveGame_whenCall_thenSuccess() {
        // when
        final Long id = racingGameDao.saveGame(3);

        // then
        assertThat(id).isNotNull();
    }

    @Test
    @DisplayName("savePlayerAll을 테스트를 진행한다.")
    void savePlayerAll_whenCall_thenSuccess() {
        // given
        final Long id = racingGameDao.saveGame(15);
        final PlayerSaveDto kongHana = new PlayerSaveDto(id, "콩하나", 10, true);
        final PlayerSaveDto ethan = new PlayerSaveDto(id, "에단", 5, false);

        // when, then
        assertThatCode(() ->  racingGameDao.saveAllPlayers(List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }
}
