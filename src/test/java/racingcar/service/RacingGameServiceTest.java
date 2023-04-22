package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.RandomNumberGenerator;
import racingcar.api.dto.request.CarGameRequest;
import racingcar.api.dto.response.GameResponse;
import racingcar.repository.GameRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RacingGameServiceTest {

    @Mock
    RandomNumberGenerator numberGenerator;

    @InjectMocks
    RacingGameService racingGameService;

    @Mock
    GameRepository gameRepository;

    @Test
    void 자동차_게임_실행_테스트() {
        int tryCount = 5;
        CarGameRequest request = new CarGameRequest("juno, glen", tryCount);

        Mockito.when(numberGenerator.generate())
                .thenReturn(4, 3);

        Mockito.when(gameRepository.save(any()))
                .thenReturn(null);

        GameResponse result = racingGameService.play(request);

        Assertions.assertThat(result.getWinners())
                .isEqualTo("juno");
    }

    @Test
    void 이름_중복_시_예외() {
        int tryCount = 5;
        CarGameRequest request = new CarGameRequest("juno, juno", tryCount);
        assertThatThrownBy(() -> racingGameService.play(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
