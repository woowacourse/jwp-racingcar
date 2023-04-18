package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.GameResultDao;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.dto.CarStatusResponseDto;
import racingcar.dto.GameHistoriesResponseDto;
import racingcar.dto.GameResultResponseDto;
import racingcar.utils.RandomPowerGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Transactional
class RacingCarServiceIntegrationTest {

    @Autowired
    RacingCarService racingCarService;

    @Autowired
    GameResultDao gameResultDao;

    @MockBean
    RandomPowerGenerator randomPowerGenerator;

    @Test
    @DisplayName("게임 이력을 조회한다.")
    void returns_all_game_histories() {
        // given
        Cars cars = Cars.from(List.of("pobi", "crong"));
        TryCount tryCount = new TryCount(3);

        List<GameHistoriesResponseDto> expectedResult = List.of(
                GameHistoriesResponseDto.toDto("pobi,crong",
                        List.of(
                                CarStatusResponseDto.toDto(new Car("pobi", 3)),
                                CarStatusResponseDto.toDto(new Car("crong", 1))
                        )
                ));

        gameResultDao.saveGame(cars, tryCount);

        // when
        List<GameHistoriesResponseDto> result = racingCarService.findAllGameHistories();

        // then
        assertAll(
                () -> assertThat(result.size()).isEqualTo(1),
                () -> assertThat(expectedResult.get(0).getWinners()).isEqualTo("pobi,crong")
        );
    }

    @Test
    @DisplayName("자동차 경주 게임을 시작하고, 결과를 반환한다.")
    void returns_result_after_start_race() {
        // given
        Cars cars = Cars.from(List.of("pobi", "crong"));
        TryCount tryCount = new TryCount(3);
        GameResultResponseDto expectedResult = GameResultResponseDto.toDto(cars);

        given(randomPowerGenerator.generateRandomPower()).willReturn(5);

        // when
        GameResultResponseDto result = racingCarService.startRace(cars, tryCount);
        List<Long> allGamesId = gameResultDao.findAllGamesId();

        // then
        assertAll(
                () -> assertThat(result.getRacingCars().size()).isEqualTo(2),
                () -> assertThat(allGamesId.size()).isEqualTo(1),
                () -> assertThat(result.getWinners().containsAll(expectedResult.getWinners())).isTrue()
        );
    }
}
