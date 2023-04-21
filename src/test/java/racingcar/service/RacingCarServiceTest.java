package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
import racingcar.domain.NumberPicker;
import racingcar.repository.JdbcRacingGameRepository;
import racingcar.service.dto.RacingCarDto;
import racingcar.service.dto.RacingCarGameResultDto;

@ExtendWith(MockitoExtension.class)
class RacingCarServiceTest {

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
        final Cars cars = new Cars(carList);

        doNothing().when(jdbcRacingGameRepository).saveGameResult(any(Cars.class));

        racingCarService.saveGameResult(cars);

        verify(jdbcRacingGameRepository).saveGameResult(cars);
    }

    @DisplayName("게임 실행하고, 반환되는 결과가 예상대로인지 확인하는 기능 테스트")
    @Test
    public void playRoundTest() {
        final List<String> playerNames = List.of(TEST_PLAYER_NAME_1, TEST_PLAYER_NAME_2);
        final int tryCount = 2;
        final NumberPicker numberPicker = new DefinedNumberPicker(List.of(4, 4, 6, 2));

        final RacingCarService racingCarService =
                new RacingCarService(mock(JdbcRacingGameRepository.class), numberPicker);

        final RacingCarGameResultDto racingCarGameResultDto = racingCarService.playRound(playerNames, tryCount);
        final List<RacingCarDto> racingCarDtos = racingCarGameResultDto.getRacingCars();

        assertAll(
                () -> assertThat(racingCarGameResultDto.getWinners())
                        .containsExactly(TEST_PLAYER_NAME_1),
                () -> assertThat(racingCarDtos)
                        .extracting(RacingCarDto::getName)
                        .containsExactly(TEST_PLAYER_NAME_1, TEST_PLAYER_NAME_2),
                () -> assertThat(racingCarDtos)
                        .extracting(RacingCarDto::getPosition)
                        .contains(1, 2)
        );
    }
}
