package racingcar.dao.game;

import org.junit.jupiter.api.*;
import racingcar.domain.racinggame.RacingGame;
import racingcar.dto.GameDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemoryGameDaoTest {
    private MemoryGameDao gameDao;
    
    @BeforeEach
    void setUp() {
        gameDao = new MemoryGameDao();
        final RacingGame firstRacingGame = new RacingGame("아벨,스플릿,포비", 20);
        gameDao.save(new GameDto(firstRacingGame));
        final RacingGame secondRacingGame = new RacingGame("아벨,스플릿", 10);
        gameDao.save(new GameDto(secondRacingGame));
    }
    
    @Test
    void 모든_GameID를_반환한다() {
        // when
        final List<Long> gameIds = gameDao.findAllId();
        
        // then
        assertThat(gameIds).containsExactly(1L, 2L);
    }
    
    @AfterEach
    void tearDown() {
        gameDao.deleteAll();
    }
}
