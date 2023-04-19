//package racingcar.controller;
//
//import static org.assertj.core.api.Assertions.assertThatCode;
//
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import racingcar.WebService;
//import racingcar.dto.NamesDto;
//import racingcar.dto.ResultDto;
//import racingcar.dto.TryCountDto;
//import racingcar.dto.WinnerDto;
//import racingcar.view.RacingCarView;
//
//class WebControllerTest {
//    private ConsoleController consoleController;
//
//    private static class MockRacingCarView implements RacingCarView {
//        @Override
//        public NamesDto receiveCarNames() {
//            return NamesDto.of("car1,car2,car3");
//        }
//
//        @Override
//        public TryCountDto receiveTryCount() {
//            return TryCountDto.of("5");
//        }
//
//        @Override
//        public void printRacingProgress(List<ResultDto> responses) {
//
//        }
//
//        @Override
//        public void printWinners(WinnerDto response) {
//
//        }
//
//        @Override
//        public void printStartMessage() {
//
//        }
//
//        @Override
//        public void printExceptionMessage(RuntimeException e) {
//
//        }
//    }
//
//    @BeforeEach
//    void setUp() {
//        consoleController = new ConsoleController();
//    }
//
//    @Test
//    @DisplayName("Controller가 정상적으로 작동해야한다.")
//    void controller_start() {
//        assertThatCode(() -> consoleController.start())
//                .doesNotThrowAnyException();
//    }
//}
