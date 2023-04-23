package racingcar.view;

import racingcar.controller.dto.CarResponseDto;

import java.util.List;

public class OutputView {

    private static final String WINNERS_FORMAT = "%s가 최종 우승했습니다.%n";
    private static final String CAR_RESULT_FORMAT = "%s : %s\n";

    public void printResult(List<CarResponseDto> carResponseDtos) {
        carResponseDtos.forEach(carResponseDto -> System.out.print(printCarResult(carResponseDto)));
        System.out.println();
    }

    private String printCarResult(CarResponseDto carResponseDto) {
        return String.format(
                CAR_RESULT_FORMAT,
                carResponseDto.getName(),
                carResponseDto.getPosition());
    }

    public void printWinners(String winners) {
        System.out.printf(WINNERS_FORMAT, winners);
    }

}
