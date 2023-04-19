package racingcar.view;

import static java.lang.System.lineSeparator;

import racingcar.dto.CarDto;
import racingcar.response.PlayResponse;
import java.util.List;

public class OutputView {

    public void printResult(final PlayResponse playResponse) {
        List<CarDto> racingCars = playResponse.getRacingCars();
        String winnerNames = playResponse.getWinners();

        System.out.println(lineSeparator() + "실행 결과");

        for (final CarDto carDto : racingCars) {
            System.out.println(carDto.getName() + " : " + carDto.getPosition());
        }
        System.out.println(winnerNames + "가 최종 우승했습니다.");
    }
}
