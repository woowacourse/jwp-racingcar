package racingcar.view;

import racingcar.dto.CarStatusDto;

import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String WINNER = "우승자: ";
    private static final String RESULT = "결과: ";
    private static final String NAME = "Name: ";
    private static final String POSITION = "Position: ";

    public static void printResultMessage() {
        System.out.println("실행 결과");
    }

    public static void printFinalResult(List<String> winnersName) {
        String winnersResult = String.join(DELIMITER, winnersName);
        System.out.println(WINNER + winnersResult);
        System.out.println();
    }

    public static void printCarStatus(List<CarStatusDto> carStatusDtos) {
        System.out.println(RESULT);
        carStatusDtos.forEach(carStatusDto -> {
            final String name = carStatusDto.getName();
            final int position = carStatusDto.getCurrentPosition();
            System.out.println(NAME + name + DELIMITER + POSITION + position);
        });
        System.out.println();
    }
}
