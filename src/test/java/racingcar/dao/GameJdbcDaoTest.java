package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.Game;
import racingcar.entity.GameId;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GameJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameJdbcDao(jdbcTemplate);
    }

    @Test
    void 게임을_저장한다() {
        // given
        final int trialCount = 5;

        Game game = new Game(trialCount);
        // when
        GameId saveGameId = gameDao.saveAndGetGameId(game);

        // then
        assertThat(saveGameId.getId()).isPositive();
    }
}
