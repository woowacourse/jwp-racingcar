package racingcar.view;

import racingcar.dto.WinnerCarDto;

public class OutputView {

    private static final String ERROR_FORMAT = "[Error] ";
    private static final String WINNER_FORMAT = "우승자: %s";
    private static final String RESULT_FORMAT = "Name: %s, Position: %s";

    public static void printResult(WinnerCarDto winnersDto) {
        String winners = winnersDto.winnerNames();

        print(String.format(WINNER_FORMAT, winners));
        print("\n결과");
        winnersDto.getRacingCars()
                .forEach(carDto -> print(String.format(RESULT_FORMAT, carDto.getName(), carDto.getPosition())));
    }

    private static void print(String message) {
        System.out.println(message);
    }

    public static void printExceptionMessage(String message) {
        print(ERROR_FORMAT + message);
    }
}
