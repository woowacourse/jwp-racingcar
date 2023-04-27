package racingcar.repository.dao;

import static org.assertj.core.api.Assertions.*;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import racingcar.repository.entity.WinnerEntity;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JdbcWinnerDaoTest {

    @Autowired
    private DataSource dataSource;

    private WinnerDao winnerDao;

    @BeforeEach
    void setUp() {
        winnerDao = new JdbcWinnerDao(dataSource);
    }

    @Test
    void save_메서드로_winner를_저장한다() {
        final Long dummyGameId = 1L;
        final Long dummyPlayerId = 1L;
        final WinnerEntity winnerEntity = new WinnerEntity(dummyGameId, dummyPlayerId);

        long winnerId = winnerDao.save(winnerEntity);

        assertThat(winnerId).isGreaterThan(0L);
    }
}
