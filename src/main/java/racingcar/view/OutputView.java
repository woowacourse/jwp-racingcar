package racingcar.view;

import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.GamePlayResponseDto;

public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();

    public void printResult(GamePlayResponseDto gameResult) {
        System.out.println("----- 경주 결과 -----");
        System.out.println("우승자: " + gameResult.getWinners() + NEW_LINE);
        for (CarDto car : gameResult.getRacingCars()) {
            System.out.println(car.getName() + ": " + car.getPosition() + "칸 이동");
        }
        System.out.println("----------------------");
    }
}
