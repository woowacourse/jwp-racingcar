package racingcar.view;

import java.util.List;
import racingcar.dto.CarDto;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_PRINT_FORMAT = "%s가 최종 우승했습니다." + LINE_SEPARATOR;
    private static final String WORD_DELIMITER = ", ";

    public static void printResult(final List<CarDto> cars, final List<String> winners) {
        System.out.println(RESULT_MESSAGE);
        cars.forEach(OutputView::printPosition);
        printWinners(winners);
    }

    private static void printPosition(final CarDto car) {
        final String name = car.getName();
        final int position = car.getPosition();

        System.out.println(name + ": " + position);
    }

    private static void printWinners(final List<String> winners) {
        System.out.printf(WINNER_PRINT_FORMAT, String.join(WORD_DELIMITER, winners));
    }
}
