package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    private static final long GAME_ID = 10L;

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
        // when, then
        assertThatCode(this::saveSampleTwoPlayer).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findAll을 테스트 한다.")
    void findAll_whenCall_thenReturnAllPlayers() {
        // given
        saveSampleTwoPlayer();

        // when
        final List<Player> players = playerDao.findAll(GAME_ID);

        // then
        assertAll(
                () -> assertThat(players).hasSize(2),
                () -> assertThat(players).usingRecursiveComparison()
                        .isEqualTo(List.of(new Player("콩하나", 10, true),
                                        new Player("에단", 5, false)))
        );
    }

    private void saveSampleTwoPlayer() {
        final Player kongHana = new Player("콩하나", 10, true);
        final Player ethan = new Player("에단", 5, false);
        playerDao.saveAllPlayers(GAME_ID, List.of(kongHana, ethan));
    }
}
