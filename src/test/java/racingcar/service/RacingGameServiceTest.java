package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.MockNumberGenerator;
import racingcar.dao.CarRepository;
import racingcar.dao.GameRepository;
import racingcar.dao.WinnerRepository;
import racingcar.dao.entity.CarEntity;
import racingcar.domain.NumberGenerator;
import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;

@ExtendWith(MockitoExtension.class)
@DisplayName("RacingGameService 테스트")
@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RacingGameServiceTest {

    private GameRepository gameRepository;
    private CarRepository carRepository;
    private WinnerRepository winnerRepository;
    private NumberGenerator numberGenerator;
    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        gameRepository = Mockito.mock(GameRepository.class);
        carRepository = Mockito.mock(CarRepository.class);
        winnerRepository = Mockito.mock(WinnerRepository.class);
        numberGenerator = new MockNumberGenerator();
        racingGameService = new RacingGameService(
                gameRepository,
                carRepository,
                winnerRepository,
                numberGenerator);
    }

    @Test
    void run은_경주를_실행하고_게임_결과를_dto로_반환한다() {
        RacingGameRequestDto request = new RacingGameRequestDto("가가, 나나, 다다, 라라", 1);
        when(gameRepository.save(any())).thenReturn(1);
        RacingGameResponseDto response = racingGameService.run(request);
        System.out.println();
        assertThat(response.getWinners()).hasSize(4);
        assertThat(response.getRacingCars()).contains(new CarStatusDto("가가", 1));
    }

    @Test
    void findAllGameResult() {
        // given
        List<CarEntity> carEntities = List.of(
                new CarEntity(1, "가가", 5, 1),
                new CarEntity(2, "나나", 5, 1),
                new CarEntity(3, "다다", 5, 2),
                new CarEntity(4, "라라", 5, 2)
        );
        given(carRepository.findCarsByGameID(1)).willReturn(carEntities);

        // when
        final List<RacingGameResponseDto> result = racingGameService.findAllGameResult();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getRacingCars()).hasSize(2);
        assertThat(result.get(0).getWinners()).hasSize(2);
        assertThat(result.get(1).getRacingCars()).hasSize(2);
        assertThat(result.get(1).getWinners()).hasSize(2);

    }

}
