package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.Car;

public class ConsoleOutputView {

    private static final String CAR_NAME_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String TRY_COUNT_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String CAR_GAME_RESULT_FORMAT = "%s : %s" + System.lineSeparator();
    private static final String DISTANCE_COMMAND = "-";
    private static final String WINNER_MESSAGE = "가 최종 우승했습니다.";
    private static final String WINNER_NAME_DELIMITER = ", ";

    public void printNameInput() {
        System.out.println(CAR_NAME_INPUT_MESSAGE);
    }

    public void printCountInput() {
        System.out.println(TRY_COUNT_INPUT_MESSAGE);
    }

    public void printResultMessage() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRoundResult(final List<Car> roundResult) {
        for (final Car car : roundResult) {
            System.out.printf(CAR_GAME_RESULT_FORMAT, car.getName().getValue()
                    , DISTANCE_COMMAND.repeat(car.getDistance().getValue()));
        }
        System.out.println();
    }

    public void printWinners(final List<String> winnerNames) {
        System.out.println(winnerNames.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(WINNER_NAME_DELIMITER))
                + WINNER_MESSAGE);
    }
}
