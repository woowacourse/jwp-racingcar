package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

public class OutputView {

    public void printResult(ResultDto result) {
        printWinners(result.getWinners());
        System.out.println("결과:");
        result.getRacingCars()
                .forEach(this::printEachCarFinalPosition);
    }

    private void printWinners(String winners) {
        System.out.println("우승자:");
        System.out.println(winners);
    }

    private void printEachCarFinalPosition(CarDto carDto) {
        String resultStatement = String.format("Name: %s, Position: %d", carDto.getName(), carDto.getPosition());
        System.out.println(resultStatement);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
