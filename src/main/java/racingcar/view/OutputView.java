package racingcar.view;

import racingcar.dto.CarStatusDto;
import racingcar.dto.RacingGameResponseDto;

import java.util.List;

public class OutputView {
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String DELIMITER = " : ";
    private static final String MOVING_SYMBOL = "-";
    private static final String WINNER_DELIMITER = ", ";
    private static final String WINNER_MESSAGE = "가 최종 우승했습니다.";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printResult(final RacingGameResponseDto responseDto) {
        System.out.println(RESULT_MESSAGE);
        printWinners(responseDto.getWinners());
        printCarStatus(responseDto.getRacingCars());
    }

    public void printCarStatus(List<CarStatusDto> racingCars) {
        racingCars.forEach(car -> {
            int moveCount = car.getPosition();
            System.out.println(car.getName() + DELIMITER + MOVING_SYMBOL.repeat(moveCount));
        });
    }

    public void printWinners(List<String> winners) {
        String winnersResult = String.join(WINNER_DELIMITER, winners);
        System.out.println(winnersResult + WINNER_MESSAGE);
    }

    public void printError(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
