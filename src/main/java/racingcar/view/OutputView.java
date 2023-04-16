package racingcar.view;

import racingcar.dto.CarStatusDto;

import java.util.List;

public class OutputView {
    private static final String WINNER_DELIMITER = ", ";

    public static void printResultMessage() {
        System.out.println("실행 결과");
    }

    public static void printWinners(List<String> winnersName) {
        String winnersResult = String.join(WINNER_DELIMITER, winnersName);
        System.out.println(winnersResult + "가 최종 우승했습니다.");
    }

    public static void printPlayerResult(List<CarStatusDto> carStatusDtos) {
        carStatusDtos.forEach(carStatusDto ->
                System.out.println(carStatusDto.getName() + "의 최종 위치는 " + carStatusDto.getCurrentPosition() + "입니다.")
        );

    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
