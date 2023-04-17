package racingcar.view;

import racingcar.model.car.Car;

import java.util.List;

public class OutputView {
    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    public void printResultMessage() {
        System.out.println("실행 결과");
    }

    public void printResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + ": " + car.getPosition());
        }
    }

    public void printWinners(List<String> winners) {
        System.out.printf("%s가 최종 우승했습니다.%n", String.join(", ", winners));
    }
}
