package racingcar.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import racingcar.model.Car;
import racingcar.model.Cars;

public class View {
    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    public void error(String errorMessage) {
        System.out.println("[ERROR] "+ errorMessage);
    }


    public List<String> carNames() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        try {
            String input = input();
            validateContainDelimiter(input);
            return split(input);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());

            return carNames();
        }
    }

    private static void validateContainDelimiter(String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException("[ERROR] 구분자(" + DELIMITER + ")가 필요해요.");
        }
    }

    private List<String> split(String input) {
        return Arrays.stream(input.split(View.DELIMITER)).collect(Collectors.toList());
    }

    public int tryCount() {
        System.out.println("시도할 횟수는 몇회인가요?");
        try {
            return inputTryCount();
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            return tryCount();
        }
    }

    private int inputTryCount() throws IllegalArgumentException {
        int tryCount;
        try {
            tryCount = Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력가능해요");
        }
        return tryCount;
    }

    private String input() {
        return scanner.nextLine();
    }

    public void printWinner(List<Car> winners) {
        String winnerNames = winners.stream()
            .map(Car::getName)
            .collect(Collectors.joining(", "));

        System.out.println(winnerNames + "가 최종 우승했습니다.");
    }

    public void printAllCars(Cars cars) {
        cars.getCars()
            .forEach(car ->
                System.out.println(car.getName() + " : " +car.getPosition()));
    }
}
