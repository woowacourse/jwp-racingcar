package racingcar.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String WINNER_GUIDE_MESSAGE = "가 최종 우승했습니다.";
    private static final String WINNER_DELIMITER = ", ";
    private static final String STATUS_GUIDE_MESSAGE = "실행 결과";

    private OutputView() {
    }

    public static void printStatusGuide() {
        System.out.println();
        System.out.println(STATUS_GUIDE_MESSAGE);
    }

    public static void printWinner(final List<String> winners) {
        System.out.print(String.join(WINNER_DELIMITER, winners));
        System.out.println(WINNER_GUIDE_MESSAGE);
    }

    public static void printStatus(final Map<String, Integer> status) {
        status.forEach((name, position) -> {
            System.out.print(name + " : ");
            for (int i = 0; i < position; i++) {
                System.out.print("-");
            }
            System.out.println();
        });
    }
}
