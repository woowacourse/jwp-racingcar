package racingcar.view;

import racingcar.dto.OneGameHistoryDto;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "winners: %s%n%n";
    private static final String RACING_CAR_INFO_FORMAT = "name: %s%nposition: %d%n%n";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public static void printWinner(final OneGameHistoryDto oneGameHistoryDto) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(oneGameHistoryDto.getWinners()));
        oneGameHistoryDto.getRacingCars()
                .forEach(racingCarDto ->
                        System.out.printf(RACING_CAR_INFO_FORMAT, racingCarDto.getName(), racingCarDto.getPosition()));
    }

    private static String makeWinnerInfo(String winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
