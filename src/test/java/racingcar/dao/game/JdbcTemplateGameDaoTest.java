package racingcar.dao.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.racinggame.RacingGame;
import racingcar.dto.GameDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@JdbcTest
class JdbcTemplateGameDaoTest {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private GameDao gameDao;
    
    @BeforeEach
    void setUp() {
        // given
        namedParameterJdbcTemplate.getJdbcTemplate().execute("ALTER TABLE GAME ALTER COLUMN id RESTART WITH 1");
        
        this.gameDao = new JdbcTemplateGameDao(namedParameterJdbcTemplate);
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
        gameDao.save(new GameDto(new RacingGame("아벨", 0)));
    }
    
    @Test
    void 모든_gameId를_반환한다() {
        // when
        final List<Long> gameIds = gameDao.findAllId();
        
        // then
        assertThat(gameIds).containsExactly(1L, 2L);
    }
}
