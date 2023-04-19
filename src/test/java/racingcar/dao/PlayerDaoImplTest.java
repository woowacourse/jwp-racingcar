package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        assertThatCode(this::saveTwoPlayers).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findAll을 테스트 한다.")
    void findAll_whenCall_thenReturnAllPlayers() {
        // given
        saveTwoPlayers();
        savePlayers(
                new Player("소니", 10, true),
                new Player("콩하나", 10, true),
                new Player("에단", 10, true)
        );

        // when
        final List<Player> players = playerDao.findAll();

        // then
        assertAll(
                () -> assertThat(players).hasSize(5),
                () -> assertThat(players).usingRecursiveComparison()
                        .ignoringFields("id", "gameId")
                        .isEqualTo(createResults())
        );
    }

    private void saveTwoPlayers() {
        savePlayers(
                new Player("콩하나", 10, true),
                new Player("에단", 5, false)
        );
    }

    private void savePlayers(Player... players) {
        playerDao.saveAllPlayers(GAME_ID, List.of(players));
    }

    private static List<Player> createResults() {
        return List.of(
                new Player("콩하나", 10, true),
                new Player("에단", 5, false),
                new Player("소니", 10, true),
                new Player("콩하나", 10, true),
                new Player("에단", 10, true));
    }
}
