package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.CarDto;
import racingcar.dto.GameResultResponse;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_PRINT_FORMAT = "%s가 최종 우승했습니다." + LINE_SEPARATOR;
    private static final String WORD_DELIMITER = ", ";

    public static void printResult(final GameResultResponse gameResultResponse) {
        System.out.println(RESULT_MESSAGE);

        final String winners = gameResultResponse.getWinners();
        final List<CarDto> cars = gameResultResponse.getRacingCars().stream()
                .map(CarDto::getInstance)
                .collect(Collectors.toList());

        cars.forEach(OutputView::printPosition);
        printWinners(winners);
    }

    private static void printPosition(final CarDto car) {
        final String name = car.getName();
        final int position = car.getPosition();

        System.out.println(name + ": " + position);
    }

    private static void printWinners(final String winners) {
        System.out.printf(WINNER_PRINT_FORMAT, winners);
    }
}
