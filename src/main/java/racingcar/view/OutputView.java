package racingcar.view;

import java.util.List;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.RacingCarGameResponse;

public class OutputView {

    private static final String RESULT_MESSAGE = "실행 결과";

    public static void printResult(final RacingCarGameResponse racingCarGameResponse) {
        System.out.println(RESULT_MESSAGE);
        String winners = racingCarGameResponse.getWinners();
        System.out.println("winners: " + winners);
        List<CarDto> carDtos = racingCarGameResponse.getRacingCars();
        System.out.println("racingCars: ");
        carDtos.forEach(System.out::println);
    }

    public static void printError(final Exception e) {
        System.out.println(e.getMessage());
    }
}
