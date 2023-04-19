package racingcar.view;

import racingcar.entity.CarEntity;
import racingcar.dto.GamePlayResponseDto;

import java.util.List;

public class OutputView {

    private static final String WINNER_FORMAT = "우승자: %s";
    private static final String RESULT_FORMAT = "Name: %s, Position: %s";

    public static void printResult(final GamePlayResponseDto gamePlayResponseDto) {
        String winners = gamePlayResponseDto.getWinners();
        final List<CarEntity> racingCars = gamePlayResponseDto.getRacingCars();

        System.out.printf((WINNER_FORMAT) + "%n", winners);
        System.out.println("\n결과");
        racingCars.forEach(OutputView::convertResult);
    }

    public static void printExceptionMessage(String message) {
        System.out.printf("[Error] %s%n", message);
    }

    private static void convertResult(final CarEntity carEntity) {
        final String format = String.format(RESULT_FORMAT, carEntity.getName(), carEntity.getPosition());
        System.out.println(format);
    }
}
