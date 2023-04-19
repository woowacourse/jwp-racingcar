package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.dao.GameResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.car.CarStatusResponseDto;
import racingcar.dto.car.GameHistoriesResponseDto;
import racingcar.dto.car.GameResultResponseDto;
import racingcar.dto.car.PlayerHistoryDto;
import racingcar.util.RandomSuccessPowerMaker;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    @InjectMocks
    RacingCarService racingCarService;

    @Mock
    RandomSuccessPowerMaker powerMaker;

    @Mock
    GameResultDao gameResultDao;

    @Test
    @DisplayName("게임 이력을 조회한다.")
    void returns_all_game_histories() {
        // given
        List<Long> allGameId = List.of(1L);

        List<PlayerHistoryDto> playerHistory = List.of(
                PlayerHistoryDto.toDto("pobi", 3, true),
                PlayerHistoryDto.toDto("crong", 1, false)
        );

        List<GameHistoriesResponseDto> expectedResult = List.of(
                GameHistoriesResponseDto.toDto("pobi",
                        List.of(
                                CarStatusResponseDto.toDto(new Car("pobi", 3)),
                                CarStatusResponseDto.toDto(new Car("crong", 1))
                        )
                ));

        given(gameResultDao.findAllGamesId()).willReturn(allGameId);
        given(gameResultDao.findPlayerHistoriesByGameId(any())).willReturn(playerHistory);

        // when
        List<GameHistoriesResponseDto> result = racingCarService.findAllGameHistories();

        // then
        assertAll(
                () -> assertThat(result.get(0).getWinners()).isEqualTo(expectedResult.get(0).getWinners()),
                () -> assertThat(result.size()).isEqualTo(1)
        );
    }

    @Test
    @DisplayName("자동차 경주 게임을 시작하고, 결과를 반환한다.")
    void returns_result_after_start_race() {
        // given
        Cars cars = Cars.from(List.of("pobi", "crong"));
        TryCount tryCount = new TryCount(3);
        GameResultResponseDto expectedResult = GameResultResponseDto.toDto(cars);
        given(gameResultDao.saveGame(cars, tryCount)).willReturn(expectedResult);

        // when
        GameResultResponseDto result = racingCarService.startRace(cars, tryCount);

        // then
        assertAll(
                () -> assertThat(result.getWinners().containsAll(List.of("pobi", "crong"))).isTrue(),
                () -> assertThat(result.getRacingCars().size()).isEqualTo(cars.getCars().size())
        );
    }
}
