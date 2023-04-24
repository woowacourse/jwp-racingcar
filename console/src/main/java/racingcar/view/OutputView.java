package racingcar.view;

import java.util.List;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStatusGuide() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public static void printWinner(final List<String> winners) {
        System.out.print(String.join(", ", winners));
        System.out.println("가 최종 우승했습니다.");
    }

    public static void printCarsStatus(final Map<String, Integer> status) {
        status.forEach(OutputView::printCarStatus);
    }

    private static void printCarStatus(final String name, final Integer position) {
        System.out.print(name + " : ");
        for (int i = 0; i < position; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
