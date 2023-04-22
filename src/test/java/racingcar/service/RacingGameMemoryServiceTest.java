package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.response.RacingGameResponse;
import racingcar.domain.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingGameMemoryServiceTest {
    @Mock
    RacingGameService racingGameService;

    @DisplayName("자동차 경주를 통해 게임 결과를 저장한 값을 반환한다.")
    @Test
    void race() {
        // given
        final RacingGameResponse response
                = new RacingGameResponse("헤나", List.of(new Car("헤나"), new Car("찰리")));

        when(racingGameService.race(any(), anyInt())).thenReturn(response);

        // when
        final RacingGameResponse racingGameResponse = racingGameService.race(List.of("헤나", "찰리"), 10);


        // expect
        assertThat(racingGameResponse)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }

    @DisplayName("콘솔에서는 레이싱 게임 결과 히스토리를 불러올 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenFindAllRacingGameHistories() {
        // given
        when(racingGameService.findAllRacingGameHistories()).thenThrow(UnsupportedOperationException.class);

        // when
        assertThatThrownBy(() -> racingGameService.findAllRacingGameHistories())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
