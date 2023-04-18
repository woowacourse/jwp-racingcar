package racing.service;

import java.util.List;
import org.assertj.core.api.Assertions;
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

    @Test
    void playGame() {
        final WebRacingGameService webRacingGameService = new WebRacingGameService(carDao, gameDao);
        final GameResultDto gameResultDto = webRacingGameService.playGame(new GameInputDto("스플릿,아벨", "5"), () -> 1);
        Assertions.assertThat(gameResultDto.getWinners()).isEqualTo("스플릿,아벨");
        Assertions.assertThat(gameResultDto.getRacingCars()).hasSize(2);
    }

    @Test
    void showGames() {
        final WebRacingGameService webRacingGameService = new WebRacingGameService(carDao, gameDao);
        webRacingGameService.playGame(new GameInputDto("스플릿,아벨", "5"), () -> 1);
        webRacingGameService.playGame(new GameInputDto("아벨,스플릿", "5"), () -> 1);
        final List<GameResultDto> gameResultDtos = webRacingGameService.showGames();
        Assertions.assertThat(gameResultDtos).hasSize(2);
        Assertions.assertThat(gameResultDtos.get(0).getWinners()).isEqualTo("스플릿,아벨");
        Assertions.assertThat(gameResultDtos.get(0).getRacingCars()).hasSize(2);
    }
}
