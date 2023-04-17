package racingcar.view;

import racingcar.domain.Name;
import racingcar.domain.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";

    public void printNotice() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRacingResult(Map<Name, Position> history) {
        for (Name name : history.keySet()) {
            Position position = history.get(name);
            System.out.println(name.getValue() + " : " + "-".repeat(position.getValue()));
        }
        System.out.println();
    }

    public void printWinner(List<Name> winners) {
        String winnerNames = findWinnerName(winners);
        System.out.printf(WINNER_MESSAGE, winnerNames.substring(1, winnerNames.length()-1));
    }

    private static String findWinnerName(List<Name> winners) {
        return winners.stream()
                .map(Name::getValue)
                .collect(Collectors.toList())
                .toString();
    }
}
