package racingcar.view;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.PlayResponseDto;

public class OutputView {

    private static final String CAR_POSITION_MESSAGE_FORMAT = "%s : %s\n";
    private static final String WINNERS_MESSAGE_FORMAT = "우승자: %s\n";
    private static final String PLAY_RECORDS_SEPARATOR = "------------------------------";

    public void printPlayResult(final PlayResponseDto playResult) {
        System.out.printf(WINNERS_MESSAGE_FORMAT, playResult.getWinners());
        for (CarDto car : playResult.getRacingCars()) {
            System.out.printf(CAR_POSITION_MESSAGE_FORMAT, car.getName(), car.getPosition());
        }
    }

    public void printPlayRecords(final List<PlayResponseDto> playRecords) {
        for (PlayResponseDto record : playRecords) {
            System.out.println(PLAY_RECORDS_SEPARATOR);
            printPlayResult(record);
        }
    }
}
