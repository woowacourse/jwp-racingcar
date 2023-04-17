package racingcar.view;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Position;

import java.util.stream.Collectors;

public final class OutputView {

    public void printTotalMovingStatus(final Cars cars) {
        print("\n실행 결과");
        for (Car car : cars.getCars()) {
            print(String.format(
                    "%s : %s",
                    car.getName(), drawMovingLength(car.getPosition()))
            );
        }
    }

    private String drawMovingLength(final Position position) {
        return "-".repeat(Math.max(0, position.getPosition()));
    }


    public void printWinners(final Cars cars) {
        final String result = cars.getCars()
                .stream()
                .map(Car::getNameValue)
                .collect(Collectors.joining(", "));

        printLineSeparator();
        print(String.format("%s가 최종 우승했습니다.", result));
    }


    private void print(String... messages) {
        for (String message : messages) {
            System.out.print(message + System.lineSeparator());
        }
    }

    private void printLineSeparator() {
        System.out.print(System.lineSeparator());
    }

    private static final class ErrorMessage {
        private static final String ERROR_HEAD = "[ERROR] ";
        private static final String UNEXPECTED_ERROR = "예기치 못한 오류가 발생했습니다.";
    }
}
