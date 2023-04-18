package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Winner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Output {
    private static final String SEPERATOR = ", ";
    private static final String MOVE_INDICATOR = "-";

    public static void printMessage(String message) {
        System.out.println(message);
    }
    public static void printWinner(Winner winner) {
        String winnerNames = winner.getWinnerNames().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(SEPERATOR));
        System.out.println(winnerNames + "가 최종 우승했습니다.");
    }

}
