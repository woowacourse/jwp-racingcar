package racingcar.view;

import racingcar.controller.dto.GameResponse;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "winners: %s%n%n";
    private static final String RACING_CAR_INFO_FORMAT = "name: %s%nposition: %d%n%n";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public void printWinner(final GameResponse gameResponse) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(gameResponse.getWinners()));
        gameResponse.getRacingCars()
                .forEach(racingCarDto ->
                        System.out.printf(RACING_CAR_INFO_FORMAT, racingCarDto.getName(), racingCarDto.getPosition()));
    }

    private String makeWinnerInfo(final String winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
