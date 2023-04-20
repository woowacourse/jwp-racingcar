package racingcar.service;

import org.junit.jupiter.api.*;
import racingcar.dao.car.MemoryCarDao;
import racingcar.dao.game.MemoryGameDao;
import racingcar.dao.winner.MemoryWinnerDao;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResultDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ConsoleRacingCarServiceTest {
    @Test
    void 모든_게임_결과를_반환한다() {
        // given
        final MemoryGameDao gameDao = new MemoryGameDao();
        final MemoryCarDao carDao = new MemoryCarDao();
        final MemoryWinnerDao winnerDao = new MemoryWinnerDao();
        final ConsoleRacingCarService consoleRacingCarService = new ConsoleRacingCarService(gameDao, carDao, winnerDao);
        
        // when
        consoleRacingCarService.playGame(new GameRequestDto("아벨,스플릿,포비", "20"), () -> true);
        final List<GameResultDto> gameResults = consoleRacingCarService.findAllGameResult();
        
        // then
        assertAll(
                () -> assertThat(gameResults).hasSize(1),
                () -> assertThat(gameResults.get(0).getCarDtos()).hasSize(3),
                () -> assertThat(gameResults.get(0).getWinnerCarDtos()).hasSize(3)
        );
        
        gameDao.deleteAll();
        carDao.deleteAll();
        winnerDao.deleteAll();
    }
}
