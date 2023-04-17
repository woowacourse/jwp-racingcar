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

    public void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void printResultMessage() {
        System.out.println(Message.OUTPUT_RESULT_MESSAGE.message);
    }

    public void printResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getName() + ": " + car.getPosition());
        }
    }

    public void printWinners(List<String> winners) {
        System.out.printf(Message.OUTPUT_WINNER.message, String.join(", ", winners));
    }

    private enum Message {
        OUTPUT_RESULT_MESSAGE("실행 결과"),
        OUTPUT_RESULT_FORMAT("%s : %s%n"),
        OUTPUT_WINNER("%s가 최종 우승했습니다.%n");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
