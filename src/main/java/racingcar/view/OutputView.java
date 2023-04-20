package racingcar.view;

import java.util.List;
import racingcar.dto.CarResponse;
import racingcar.dto.PlayResponse;

public class OutputView {

    public void printResult(PlayResponse playResponse) {
        System.out.println("실행 결과");
        String delimiter = ":  ";
        List<CarResponse> cars = playResponse.getRacingCars();
        for (CarResponse car : cars) {
            System.out.println(car.getName() + delimiter + convertDistance(car.getPosition()));
        }
        System.out.println(playResponse.getWinners() + "가 최종 우승했습니다.");
    }

    private String convertDistance(int distance) {
        final String DISTANCE = "-";
        return DISTANCE.repeat(distance);
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
