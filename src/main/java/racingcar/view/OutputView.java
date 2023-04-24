package racingcar.view;

import racingcar.dto.CarDto;

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

    public void printResult(List<CarDto> carDtos) {
        for (CarDto carDto : carDtos) {
            System.out.println(carDto.getName() + ": " + carDto.getPosition());
        }
    }

    public void printWinners(List<String> winners) {
        System.out.printf("%s가 최종 우승했습니다.%n", String.join(", ", winners));
    }
}
