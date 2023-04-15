package racingcar.view;

import static java.util.stream.Collectors.joining;

import racingcar.domain.Car;
import java.util.List;

public class OutputView {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WIN_MENTION = "%s가 최종 우승했습니다.%n";
    private static final String WINNER_CONNECTOR = ", ";

    public void resultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void winner(List<Car> winners) {
        System.out.printf(WIN_MENTION, convertwinnersToString(winners));
    }

    private String convertwinnersToString(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(joining(WINNER_CONNECTOR));
    }
}
