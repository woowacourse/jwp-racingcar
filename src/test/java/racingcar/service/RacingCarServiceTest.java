package racingcar.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.DefinedNumberPicker;
import racingcar.domain.TryCount;
import racingcar.repository.JdbcRacingGameRepository;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

    private static final long gameId = 1L;
    private static final String TEST_PLAYER_NAME_1 = "hong";
    private static final String TEST_PLAYER_NAME_2 = "coda";

    @Mock
    private JdbcRacingGameRepository jdbcRacingGameRepository;
    @InjectMocks
    private RacingCarService racingCarService;

    @DisplayName("실제 자동차 게임 실행후 디비에 잘 저장하는지 테스트")
    @Test
    public void saveTest() {
        final List<Car> carList = List.of(new Car(TEST_PLAYER_NAME_1, 1), new Car(TEST_PLAYER_NAME_2, 2));
        final int tryCountValue = 5;
        final Cars cars = new Cars(carList);
        final TryCount tryCount = new TryCount(tryCountValue);

        doNothing().when(jdbcRacingGameRepository).saveGameResult(any(Cars.class), any(TryCount.class));

        racingCarService.saveGameResult(cars, tryCount);

        verify(jdbcRacingGameRepository).saveGameResult(cars, tryCount);
    }

    @DisplayName("tryCount만큼 Cars의 runForword메서드를 실행시켰는지 테스트")
    @Test
    public void playRoundTest() {
        final Cars cars = mock(Cars.class);
        final TryCount tryCount = new TryCount(5);

        racingCarService.playRound(cars, tryCount, new DefinedNumberPicker(Collections.emptyList()));

        verify(cars, times(5)).runRound(any(DefinedNumberPicker.class));
    }
}
