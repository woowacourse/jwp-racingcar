package racing.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import racing.dao.car.CarDao;
import racing.dao.game.GameDao;
import racing.dto.GameInputDto;
import racing.dto.GameResultDto;

@Sql("/resetTable.sql")
@SpringBootTest
class WebRacingGameServiceTest {

    @Autowired
    CarDao carDao;

    @Autowired
    GameDao gameDao;

    @DisplayName("게임을 플레이 하면 승자와 참가 차량에 대한 정보를 반환한다.")
    @Test
    void playGame() {
        //given
        final WebRacingGameService webRacingGameService = new WebRacingGameService(carDao, gameDao);

        //when
        final GameResultDto gameResultDto = webRacingGameService.playGame(new GameInputDto("스플릿,아벨", "5"), () -> 1);

        //then
        Assertions.assertThat(gameResultDto.getWinners()).isEqualTo("스플릿,아벨");
        Assertions.assertThat(gameResultDto.getRacingCars()).hasSize(2);
    }

    @DisplayName("플레이한 모든 게임의 정보를 반환한다.")
    @Test
    void showGames() {
        //given
        final WebRacingGameService webRacingGameService = new WebRacingGameService(carDao, gameDao);
        webRacingGameService.playGame(new GameInputDto("스플릿,아벨", "5"), () -> 1);
        webRacingGameService.playGame(new GameInputDto("아벨,스플릿", "5"), () -> 1);

        //when
        final List<GameResultDto> gameResultDtos = webRacingGameService.showGames();

        //then
        Assertions.assertThat(gameResultDtos).hasSize(2);
        Assertions.assertThat(gameResultDtos.get(0).getWinners()).isEqualTo("스플릿,아벨");
        Assertions.assertThat(gameResultDtos.get(0).getRacingCars()).hasSize(2);
    }
}
