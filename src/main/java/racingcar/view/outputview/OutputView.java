package racingcar.view.outputview;

import java.util.List;
import java.util.StringJoiner;
import racingcar.controller.CarResponse;
import racingcar.controller.TrackResponse;
import racingcar.exception.CustomException;

public class OutputView {
    public static final String POSITION_CAR_FORMAT_SYMBOL = "-";
    public static final String POSITION_CAR_STATE_FORMAT = "%s : %s";
    private static final String WINNER_MESSAGE = "%s가 최종 우승했습니다.";
    private static final String LINE_BREAK = "\n";

    private OutputView() {
    }

    public static void printErrorMessage(CustomException exception) {
        System.out.println(exception.getMessage());
    }

    public static void printCurrentCarsPosition(final TrackResponse trackResponse) {
        StringJoiner stringJoiner = new StringJoiner(LINE_BREAK);

        List<CarResponse> cars = trackResponse.getRacingCars();
        cars.forEach(car -> stringJoiner.add(String.format(POSITION_CAR_STATE_FORMAT,
                car.getName(),
                POSITION_CAR_FORMAT_SYMBOL.repeat(car.getPosition()))));
        System.out.println(stringJoiner + LINE_BREAK);
    }


    public static void printWinnerCars(final TrackResponse trackResponse) {
        System.out.printf((WINNER_MESSAGE) + "%n", trackResponse.getWinners());
    }
}
