package racingcar.view;

import racingcar.dto.response.CarResponseDto;
import racingcar.dto.response.GameResultResponseDto;

public final class OutputView {

    public void printTotalMovingStatus(final GameResultResponseDto dto) {
        println("\n실행 결과");
        for (CarResponseDto car : dto.getRacingCars()) {
            println(car.getName() + " : " + drawMovingLength(car.getPosition()));
        }
        printLineSeparator();
        final String winners = String.join(", ", dto.getWinners());
        println(String.format("%s가 최종 우승했습니다.", winners));
    }

    private String drawMovingLength(final int position) {
        return "-".repeat(Math.max(0, position));
    }


    private void println(String... messages) {
        for (String message : messages) {
            System.out.print(message + System.lineSeparator());
        }
    }

    private void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    private static final class ErrorMessage {
        private static final String ERROR_HEAD = "[ERROR] ";
        private static final String UNEXPECTED_ERROR = "예기치 못한 오류가 발생했습니다.";
    }
}
