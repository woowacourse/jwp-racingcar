package racingcar.view;

import java.util.List;

import racingcar.dto.CarDto;
import racingcar.dto.WinnersAndCarsDto;

public class OutputView {

    private static final String RESULT_MESSAGE = "실행 결과";

    public static void printResult(final WinnersAndCarsDto winnersAndCarsDto) {
        System.out.println(RESULT_MESSAGE);
        String winners = winnersAndCarsDto.getWinners();
        System.out.println("winners: " + winners);
        List<CarDto> carDtos = winnersAndCarsDto.getRacingCars();
        System.out.println("racingCars: ");
        carDtos.forEach(System.out::println);
    }
}
