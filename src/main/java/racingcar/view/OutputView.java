package racingcar.view;

import java.util.List;

import racingcar.dto.CarDto;
import racingcar.dto.ResponseDto;

public class OutputView {

    private static final String RESULT_MESSAGE = "실행 결과";

    public static void printResult(final ResponseDto responseDto) {
        System.out.println(RESULT_MESSAGE);
        String winners = responseDto.getWinners();
        System.out.println("winners: " + winners);
        List<CarDto> carDtos = responseDto.getRacingCars();
        System.out.println("racingCars: ");
        carDtos.forEach(System.out::println);
    }
}
