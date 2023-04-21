package racingcar.view;

import racingcar.dto.ResultDto;

public class OutputView {

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printGameResult(ResultDto resultDto) {
        resultDto.getRacingCars().forEach(carDto -> System.out.println(carDto.getName() + ": " + carDto.getPosition()));
        System.out.println("우승자: " + resultDto.getWinners());
    }
}
