package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

import java.util.List;

public class OutputView {

    private static final String WINNER_MESSAGE = "가 최종 우승했습니다.";

    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void printWinners(ResultDto dto) {

        List<CarDto> racingCars = dto.getRacingCars();
        for (CarDto racingCar : racingCars) {
            System.out.println(racingCar.getName() + " : " + "-".repeat(racingCar.getPosition()));
        }

        print(String.join(", ", dto.getWinners()) + WINNER_MESSAGE);
    }

    private void print(String message) {
        System.out.println(message);
    }

}
