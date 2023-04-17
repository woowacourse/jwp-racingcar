package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingGameJdbcServiceTest {

    @Mock
    RacingGameService service;

    @DisplayName("자동차 경주를 통해 게임 결과를 반환한다.")
    @Test
    void race() {
        // given
        final RacingGameResponse response
                = new RacingGameResponse("헤나", List.of(new Car("헤나"), new Car("찰리")));

        when(service.race(any(), anyInt())).thenReturn(response);

        // when
        final RacingGameResponse racingGameResponse = service.race(new Cars("헤나,찰리"), 10);

        // then
        assertThat(racingGameResponse)
                .usingRecursiveComparison()
                .isEqualTo(response);
    }
}
