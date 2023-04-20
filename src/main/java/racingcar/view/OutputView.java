package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RacingGameResponseDto;

public class OutputView {
    private final static String PRINT_CAR_POSITION = "%s : %d" + System.lineSeparator();
    private final static String PRINT_WINNER = "%s가 최종 우승했습니다." + System.lineSeparator();

    public void printResult(RacingGameResponseDto racingGameResponseDto) {
        System.out.printf(PRINT_WINNER, racingGameResponseDto.getWinners());
        for (CarDto car : racingGameResponseDto.getRacingCars()) {
            System.out.printf(PRINT_CAR_POSITION, car.getName(), car.getPosition());
        }
    }
}
