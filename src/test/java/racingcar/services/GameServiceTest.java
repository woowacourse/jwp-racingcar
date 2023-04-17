package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.dto.ResultDto;

class GameServiceTest {

    GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(
                new TestGameDao(),
                new TestCarDao(),
                new TestWinnerDao(),
                new TestMoveManager(List.of(true, false, false))
        );
    }

    @Test
    @DisplayName("게임의 결과를 반환한다.")
    void getAllResults() {
        List<ResultDto> allResults = gameService.getAllResults();

        ResultDto resultDto = allResults.get(0);

        Assertions.assertAll(
                () -> assertThat(allResults).hasSize(1),
                () -> assertThat(resultDto.getWinners()).isEqualTo("이리내"),
                () -> assertThat(resultDto.getRacingCars()).hasSize(2),
                () -> assertThat(resultDto.getRacingCars().get(0).getName()).isEqualTo("폴로"),
                () -> assertThat(resultDto.getRacingCars().get(0).getPosition()).isEqualTo(4),
                () -> assertThat(resultDto.getRacingCars().get(1).getName()).isEqualTo("이리내"),
                () -> assertThat(resultDto.getRacingCars().get(1).getPosition()).isEqualTo(6)
        );
    }
}
