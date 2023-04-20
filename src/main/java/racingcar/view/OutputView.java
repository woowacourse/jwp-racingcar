package racingcar.view;

import racingcar.dto.response.CarResponse;
import racingcar.dto.response.GameResponse;

import java.util.List;

public class OutputView {
    public static void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void printCarGameResult(final GameResponse gameResponse) {
        printWinners(gameResponse.getWinners());
        printCarResults(gameResponse.getRacingCars());
    }

    public static void printWinners(final String winnersName) {
        System.out.println(String.format("%s가 최종 우승했습니다.", winnersName));
    }

    private static void printCarResults(final List<CarResponse> cars) {
        for (CarResponse carResult : cars) {
            System.out.println(String.format("%s의 최종 포지션 : %d", carResult.getName(), carResult.getPosition()));
        }
    }
}
