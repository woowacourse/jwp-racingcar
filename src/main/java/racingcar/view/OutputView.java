package racingcar.view;

import racingcar.dto.CarDto;

import java.util.List;

public class OutputView {
    public static final String GAME_RESULT_FORMAT = "\nName: %s, Position: %d\n";
    public static final String WINNER_FORMAT = "우승자: %s\n";


    private OutputView() {
    }

    public static void printWinners(String winners) {
        System.out.printf(WINNER_FORMAT, winners);
    }

    public static void printRacing(List<CarDto> racingCars) {
        for (CarDto car : racingCars) {
            int position = car.getPosition();
            System.out.printf(GAME_RESULT_FORMAT, car.getName(), position);
        }
    }
}
