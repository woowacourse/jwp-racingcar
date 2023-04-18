package racingcar.view;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingGameResponse;
import racingcar.domain.Name;
import racingcar.domain.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";

    public void printNotice() {
        System.out.println("실행 결과");
    }

    public void printRacingResult(final RacingGameResponse response) {
        final List<CarDto> cars = response.getRacingCars();
        cars.forEach(this::printStatusBy);
        System.out.printf("%n%n %s가 최종 우승했습니다.%n", response.getWinners());
    }

    private void printStatusBy(final CarDto car) {
        System.out.printf("%s : %s%n", car.getName(), "-".repeat(car.getPosition()));
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
