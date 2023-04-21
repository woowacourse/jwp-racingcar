package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import racingcar.dao.car.CarDao;
import racingcar.dao.entity.Car;
import racingcar.dao.entity.Winner;
import racingcar.dao.game.GameDao;
import racingcar.dao.winner.WinnerDao;
import racingcar.domain.manager.CarMoveManager;
import racingcar.dto.ResultDto;

@ExtendWith(MockitoExtension.class)
class MockitoGameServiceTest {
    GameService gameService;
    @Mock
    GameDao gameDao;
    @Mock
    CarDao carDao;
    @Mock
    WinnerDao winnerDao;
    @Mock
    CarMoveManager carMoveManager;

    @BeforeEach
    void setUp() {
        gameService = new GameService(gameDao, carDao, winnerDao, carMoveManager);
    }

    @Test
    @DisplayName("게임의 결과를 반환한다.")
    void getAllResults() {
        when(gameDao.getGameIds()).thenReturn(List.of(1L));
        when(carDao.findAllCars()).thenReturn(List.of(
                new Car(1L, "폴로", 4),
                new Car(1L, "이리내", 6)
        ));
        when(winnerDao.findAllWinner()).thenReturn(List.of(new Winner(1L, "이리내")));

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
