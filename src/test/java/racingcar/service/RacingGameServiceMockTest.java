package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.game.RacingGame;
import racingcar.domain.game.RandomNumberGenerator;
import racingcar.dto.RacingCarDto;
import racingcar.dto.RacingGameDto;
import racingcar.repository.RacingGameRepository;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceMockTest {

    @Mock
    private RacingGameRepository racingGameRepository;

    private RacingGameService racingGameService;

    @BeforeEach
    void setUp() {
        racingGameService = new RacingGameService(racingGameRepository, new RandomNumberGenerator());
    }

    @DisplayName("게임 결과를 저장한다.")
    @Test
    void insertRacingGameResult() {
        //given
        List<String> carNames = List.of("로지", "바론");
        given(racingGameRepository.create(any(RacingGame.class)))
                .willReturn(RacingGame.of(10, carNames));
        //when
        RacingGameDto result = racingGameService.play(10, carNames);
        //then
        assertAll(
                () -> assertThat(result.getRacingCars()).hasSize(2),
                () -> assertThat(result.getWinnerNames()).isNotEmpty()
        );
    }

    @DisplayName("게임 이력을 조회할 수 있다.")
    @Test
    void readRacingGameResult() {
        //given
        given(racingGameRepository.findAll()).willReturn(
                List.of(RacingGame.of(10, List.of("이름", "이름2", "이름3")))
        );

        //when
        List<RacingGameDto> racingGames = racingGameService.readGameHistory();
        //then
        RacingGameDto readRacingGame = racingGames.get(0);
        List<RacingCarDto> racingCarDtos = readRacingGame.getRacingCars();
        List<String> names = racingCarDtos.stream().map(RacingCarDto::getName).collect(Collectors.toList());
        assertThat(names).containsExactlyInAnyOrder("이름", "이름2", "이름3");

    }
}
