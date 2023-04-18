package racingcar.view;

import java.util.List;
import racingcar.dto.GameResponseDto;
import racingcar.dto.RacingCarDto;

public class OutputView {
    private static final String ERROR_HEADER = "[ERROR] ";
    private static final String CAR_POSITION_DELIMITER = "-";


    public void printErrorMessage(String message) {
        System.out.println(ERROR_HEADER + message);
    }

    public void printResult(final GameResponseDto responseDto) {
        final List<RacingCarDto> racingCars = responseDto.getRacingCars();
        for (RacingCarDto racingCar : racingCars) {
            System.out.println(racingCar.getName() + CAR_POSITION_DELIMITER.repeat(racingCar.getPosition()));
        }
        System.out.println(responseDto.getWinners());
    }
}
