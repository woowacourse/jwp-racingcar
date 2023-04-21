package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.dao.car.CarDao;
import racingcar.dao.game.GameDao;
import racingcar.dao.winner.WinnerDao;
import racingcar.dto.GameInfoDto;
import racingcar.dto.ResultDto;

@SpringBootTest
class SpringBootGameServiceTest {

    GameService gameService;
    @Autowired
    GameDao gameDao;
    @Autowired
    CarDao carDao;
    @Autowired
    WinnerDao winnerDao;

    @BeforeEach
    void setUp() {
        List<Boolean> booleans = List.of(true, false, true, false);
        gameService = new GameService(gameDao, carDao, winnerDao, new TestMoveManager(booleans));
    }

    @Test
    void getAllResults() {
        GameInfoDto gameInfoDto = new GameInfoDto("폴로,이리내", "2");
        gameService.play(gameInfoDto);

        List<ResultDto> allResults = gameService.getAllResults();
        ResultDto resultDto = allResults.get(0);

        Assertions.assertAll(
                () -> assertThat(allResults).hasSize(1),
                () -> assertThat(resultDto.getWinners()).isEqualTo("폴로"),
                () -> assertThat(resultDto.getRacingCars()).hasSize(2),
                () -> assertThat(resultDto.getRacingCars().get(0).getName()).isEqualTo("폴로"),
                () -> assertThat(resultDto.getRacingCars().get(0).getPosition()).isEqualTo(2),
                () -> assertThat(resultDto.getRacingCars().get(1).getName()).isEqualTo("이리내"),
                () -> assertThat(resultDto.getRacingCars().get(1).getPosition()).isEqualTo(0)
        );
    }
}
