package racingcar.view;

import racingcar.dto.GameHistoryDto;

public class OutputView {

    private static final String WINNER_INFO_FORMAT = "winners: %s%n%n";
    private static final String RACING_CAR_INFO_FORMAT = "name: %s%nposition: %d%n%n";
    private static final String WINNER_INFO_DELIMITER = ", ";

    public void printWinner(final GameHistoryDto gameHistoryDto) {
        System.out.printf(WINNER_INFO_FORMAT, makeWinnerInfo(gameHistoryDto.getWinners()));
        gameHistoryDto.getRacingCars()
                .forEach(racingCarDto ->
                        System.out.printf(RACING_CAR_INFO_FORMAT, racingCarDto.getName(), racingCarDto.getPosition()));
    }

    private String makeWinnerInfo(final String winners) {
        return String.join(WINNER_INFO_DELIMITER, winners);
    }
}
