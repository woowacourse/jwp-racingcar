package racingcar.view;

import racingcar.dto.CarDTO;

import java.util.List;

public class OutputView {

    public void printRacingResult(final String winners, final List<CarDTO> cars) {
        System.out.println("실행 결과");
        System.out.println("우승자 : " + winners);
        for (CarDTO car : cars) {
            System.out.println("이름 : " + car.getName());
            System.out.println("위치 : " + car.getPosition());
        }
    }
}
