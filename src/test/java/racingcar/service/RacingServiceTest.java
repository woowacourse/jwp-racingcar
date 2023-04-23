package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.controller.dto.CarResponse;
import racingcar.controller.dto.TrackRequest;
import racingcar.controller.dto.TrackResponse;
import racingcar.dao.RacingDao;
import racingcar.dao.dto.TrackDto;
import racingcar.mapper.TrackRequestMapper;
import racingcar.model.car.strategy.MovingStrategy;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacingServiceTest {

    @InjectMocks
    private RacingService racingService;

    @Mock
    private RacingDao racingDao;

    @Mock
    private MovingStrategy movingStrategy;

    @Test
    @DisplayName("자동차 게임이 정상적으로 작동한다.")
    void playSuccess() {
        // given
        final String names = "gray,hoy,logan";
        final TrackRequest trackRequest = TrackRequestMapper.of(names, "10");
        final int expected = names.split(",").length;

        when(racingDao.save((TrackDto) any())).thenReturn(1);
        when(movingStrategy.movable()).thenReturn(true);

        // when
        final TrackResponse trackResponse = racingService.play(trackRequest);
        final List<CarResponse> carResponses = trackResponse.getRacingCars();
        final int actual = carResponses.size();

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
