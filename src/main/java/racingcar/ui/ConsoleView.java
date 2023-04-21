package racingcar.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import racingcar.service.CarEntity;
import racingcar.service.RacingResponse;

public class ConsoleView {
    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    private static void validateContainDelimiter(String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException("[ERROR] 구분자(" + DELIMITER + ")가 필요해요.");
        }
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

    private String input() {
        return scanner.nextLine();
    }

    private List<String> split(String input) {
        return Arrays.stream(input.split(ConsoleView.DELIMITER)).collect(Collectors.toList());
    }

    public void error(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
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

    public void printResult(RacingResponse racingResponse) {
        printAllCars(racingResponse.getRacingCars());
        printWinner(racingResponse.getWinners());
    }

    private void printWinner(String winners) {
        System.out.println(winners + "가 최종 우승했습니다.");
    }

    private void printAllCars(List<CarEntity> racingCars) {
        racingCars
            .forEach(car ->
                System.out.println(car.getName() + " : " + car.getPosition()));
    }

}
