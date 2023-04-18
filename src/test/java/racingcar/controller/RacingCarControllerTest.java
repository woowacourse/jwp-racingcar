package racingcar.controller;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.RacingCarNamesDto;
import racingcar.dto.RacingCarStatusDto;
import racingcar.dto.RacingCarWinnerDto;
import racingcar.dto.TryCountDto;
import racingcar.view.RacingCarView;

class RacingCarControllerTest {
    private RacingCarController racingCarController;

    private static class MockRacingCarView implements RacingCarView {
        @Override
        public RacingCarNamesDto receiveCarNames() {
            return RacingCarNamesDto.of("car1,car2,car3");
        }

        @Override
        public TryCountDto receiveTryCount() {
            return TryCountDto.of("5");
        }

        @Override
        public void printRacingProgress(List<RacingCarStatusDto> responses) {

        }

        @Override
        public void printWinners(RacingCarWinnerDto response) {

        }

        @Override
        public void printStartMessage() {

        }

        @Override
        public void printExceptionMessage(RuntimeException e) {

        }
    }

    @BeforeEach
    void setUp() {
        racingCarController = new RacingCarController(new MockRacingCarView());
    }

    @Test
    @DisplayName("Controller가 정상적으로 작동해야한다.")
    void controller_start() {
        assertThatCode(() -> racingCarController.start())
                .doesNotThrowAnyException();
    }
}
