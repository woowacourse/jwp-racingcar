package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.dto.PlayerSaveDto;
import racingcar.exception.ExceptionInformation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
class JdbcPlayerDaoTest {

    private PlayerDao playerDao;
    private GameDao gameDao;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playerDao = new JdbcPlayerDao(jdbcTemplate);
        gameDao = new JdbcGameDao(jdbcTemplate);
        gameDao.save(10);
    }

    @Test
    @DisplayName("Player 테이블에 값을 저장할 수 있다.")
    void saveGame_whenCall_thenSuccess() {
        // given
        final PlayerSaveDto kongHana = new PlayerSaveDto("콩하나", 10, true);
        final PlayerSaveDto ethan = new PlayerSaveDto("에단", 5, false);

        // when, then
        assertThatCode(() -> playerDao.save(1, List.of(kongHana, ethan)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Game 테이블에 저장되지 않은 gameId를 사용하면 Player 테이블에 값을 저장할 수 없다.")
    void saveGame_whenInvalidGameId_thenFail() {
        // given
        final PlayerSaveDto kongHana = new PlayerSaveDto("콩하나", 10, true);
        final PlayerSaveDto ethan = new PlayerSaveDto("에단", 5, false);

        // when, then
        assertThatThrownBy(() -> playerDao.save(2, List.of(kongHana, ethan)))
                .isInstanceOf(DataAccessException.class);
    }
}
