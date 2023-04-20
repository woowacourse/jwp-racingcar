package racingcar.view;

import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.dto.RacingCarDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "winners: %s%n%n";
    private static final String RACING_CAR_INFO_FORMAT = "name: %s%nposition: %d%n%n";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public static void printWinner(final RacingCars racingCars) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(racingCars.getWinnerNames()));
        racingCars.getRacingCars().stream()
                .map(racingCar -> new RacingCarDto(racingCar.getName(), racingCar.getPosition()))
                .forEach(racingCarDto ->
                        System.out.printf(RACING_CAR_INFO_FORMAT, racingCarDto.getName(), racingCarDto.getPosition()));
    }

    private static String makeWinnerInfo(List<String> winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
