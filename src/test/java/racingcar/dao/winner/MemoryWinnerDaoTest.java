package racingcar.dao.winner;

import org.junit.jupiter.api.*;
import racingcar.dto.WinnerDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemoryWinnerDaoTest {
    private MemoryWinnerDao winnerDao;
    
    @BeforeEach
    void setUp() {
        winnerDao = new MemoryWinnerDao();
        winnerDao.save(new WinnerDto(1L, 1L));
        winnerDao.save(new WinnerDto(1L, 2L));
        winnerDao.save(new WinnerDto(1L, 3L));
        winnerDao.save(new WinnerDto(2L, 1L));
        winnerDao.save(new WinnerDto(2L, 2L));
    }
    
    @Test
    void GameId를_전달하면_WinnerDtos를_전달한다() {
        // when
        final List<WinnerDto> winnerDtos = winnerDao.findWinnerDtosByGameId(2L);
        
        // then
        assertThat(winnerDtos).containsExactly(new WinnerDto(2L, 1L), new WinnerDto(2L, 2L));
    }
    
    @AfterEach
    void tearDown() {
        winnerDao.deleteAll();
    }
}
