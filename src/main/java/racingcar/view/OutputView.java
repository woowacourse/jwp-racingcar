package racingcar.view;

import racingcar.dto.CarDto;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = "-";
    private static final String WINNER_MSG = "%s가 최종 우승했습니다.";
    private static final String FORMAT = "%s : %s";

    public static void printResultMessage() {
        System.out.println("실행 결과");
    }

    public static void printAllCars(List<CarDto> cars) {
        for (CarDto car : cars) {
            System.out.printf((FORMAT) + System.lineSeparator(), car.getName(), drawCarPosition(car.getPosition()));
        }
        System.out.println();
    }

    public static void printWinners(String winners) {
        System.out.printf((WINNER_MSG) + System.lineSeparator(), winners);
    }

    private static String drawCarPosition(int position) {
        return DELIMITER.repeat(Math.max(0, position));
    }
}
