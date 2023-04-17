package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.GameEntity;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JdbcGameDaoTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new JdbcGameDao(jdbcTemplate);
    }


    @Test
    void 게임을_저장한다() {
        // given
        final int trialCount = 5;

        // when
        final Long id = gameDao.insert(GameEntity.create(trialCount));

        // then
        assertThat(id).isNotNull();
    }

    @Test
    void 저장된_게임의_수를_알_수_있다() {
        // given
        jdbcTemplate.execute("DELETE FROM GAME");

        gameDao.insert(GameEntity.create(5));
        gameDao.insert(GameEntity.create(5));
        gameDao.insert(GameEntity.create(5));

        // when
        final int count = gameDao.countGames();

        // then
        assertThat(count).isEqualTo(3);
    }
}
