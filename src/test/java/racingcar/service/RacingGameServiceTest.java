package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.RandomNumberGenerator;
import racingcar.dto.request.CarGameRequest;
import racingcar.dto.response.CarGameResponse;
import racingcar.mapper.CarResultMapper;
import racingcar.mapper.PlayResultMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RacingGameServiceTest {

    @Mock
    CarResultMapper carResultMapper;

    @Mock
    PlayResultMapper playResultMapper;

    @Mock
    RandomNumberGenerator numberGenerator;

    @InjectMocks
    RacingGameService racingGameService;

    @Test
    void 자동차_게임_실행_테스트() {
        int tryCount = 5;
        CarGameRequest request = new CarGameRequest("juno, glen", tryCount);

        Mockito.when(numberGenerator.generate())
                .thenReturn(4, 3);

        Mockito.when(carResultMapper.save(any()))
                .thenReturn(1L);

        Mockito.when(playResultMapper.save(any()))
                .thenReturn(1L);

        CarGameResponse result = racingGameService.play(request);

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
