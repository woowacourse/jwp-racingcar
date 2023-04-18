package racingcar.view;

import java.util.List;

public class OutputView {

    private static final String WINNER_DELIMITER = ",";
    private static final String WINNER_MESSAGE_FORMAT = "우승자: %s";
    private static final String CAR_MESSAGE_FORMAT = "[이름]: %s - [이동거리]: %d";

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printWinnersResult(final List<String> winners) {
        final String winnerNames = String.join(WINNER_DELIMITER, winners);
        printMessage(String.format(WINNER_MESSAGE_FORMAT, winnerNames));
    }

    public static void printCarStatus(final String name, final int position) {
        printMessage(String.format(CAR_MESSAGE_FORMAT, name, position));
    }
}
