package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class GameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(jdbcTemplate);
    }

    @Test
    void 게임을_저장한다() {
        // given
        final int trialCount = 5;
        final String winners = "비버";

        // when
        int id = gameDao.save(trialCount, winners);

        // then
        assertThat(id).isPositive();
    }
}
