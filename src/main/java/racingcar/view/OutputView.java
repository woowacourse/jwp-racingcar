package racingcar.view;

import org.springframework.stereotype.Component;
import racingcar.controller.dto.CarDto;
import racingcar.controller.dto.GamePlayResponseDto;

@Component
public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();

    public void printCarNameInputMessage() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void printTryCountInputMessage() {
        System.out.println("시도할 회수는 몇회인가요?");
    }

    public void printResult(GamePlayResponseDto gameResult) {
        System.out.println("----- 경주 결과 -----");
        System.out.println("우승자: " + gameResult.getWinners() + NEW_LINE);
        for (CarDto car : gameResult.getRacingCars()) {
            System.out.println(car.getName() + ": " + car.getPosition() + "칸 이동");
        }
        System.out.println("----------------------");
    }

    public void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
        System.out.println();
    }
}
