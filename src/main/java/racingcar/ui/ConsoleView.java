package racingcar.ui;

import java.util.List;
import java.util.Scanner;

import racingcar.dto.RacingCarRequestDto;
import racingcar.dto.RacingCarResponseDto;
import racingcar.entity.CarEntity;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    private String input() {
        return scanner.nextLine();
    }

    public void error(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public RacingCarRequestDto getRequest() {
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        String carNames = input();
        System.out.println("시도할 횟수는 몇회인가요?");
        String tryCount = input();
        return new RacingCarRequestDto(carNames, tryCount);
    }

    public void printResult(RacingCarResponseDto racingCarResponseDto) {
        printAllCars(racingCarResponseDto.getRacingCars());
        printWinner(racingCarResponseDto.getWinners());
    }

    private void printAllCars(List<CarEntity> racingCars) {
        racingCars
            .forEach(car ->
                System.out.println(car.getName() + " : " + car.getPosition()));
    }

    private void printWinner(String winners) {
        System.out.println(winners + "가 최종 우승했습니다.");
    }
}
