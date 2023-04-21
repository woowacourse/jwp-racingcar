package racingcar.service;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import racingcar.dao.car.MemoryCarDao;
import racingcar.dao.game.MemoryGameDao;
import racingcar.dao.winner.MemoryWinnerDao;
import racingcar.dto.GameRequestDto;
import racingcar.dto.GameResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingCarServiceTest {
    @Test
    void 모든_게임_결과를_반환한다() {
        // given
        final MemoryGameDao gameDao = new MemoryGameDao();
        final MemoryCarDao carDao = new MemoryCarDao();
        final MemoryWinnerDao winnerDao = new MemoryWinnerDao();
        final RacingCarService racingCarService = new RacingCarService(gameDao, carDao, winnerDao);
        
        // when
        racingCarService.playGame(new GameRequestDto("아벨,스플릿,포비", "20"), () -> true);
        final List<GameResponseDto> gameResponseDtos = racingCarService.findAllGameResult();
        
        // then
        assertAll(
                () -> assertThat(gameResponseDtos).hasSize(1),
                () -> assertThat(gameResponseDtos.get(0).getRacingCars()).hasSize(3),
                () -> assertThat(gameResponseDtos.get(0).getWinners()).isEqualTo("아벨, 스플릿, 포비")
        );
        
        gameDao.deleteAll();
        carDao.deleteAll();
        winnerDao.deleteAll();
    }
}
