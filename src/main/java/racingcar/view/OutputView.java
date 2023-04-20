package racingcar.view;

import racingcar.dto.CarDTO;

import java.util.List;

public class OutputView {

    public void printCarNameInputGuide() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
    }

    public void printGameRoundGuide() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public void printRacingResult(final String winners, final List<CarDTO> cars) {
        System.out.println("실행 결과");
        System.out.println("우승자 : " + winners);
        for (CarDTO car : cars) {
            System.out.println("이름 : " + car.getName());
            System.out.println("위치 : " + car.getPosition());
        }
    }
}
