package racingcar.view;

import racingcar.controller.dto.CarResponseDto;
import racingcar.controller.dto.GameResponseDto;

public class OutputView {

    private static final String RESULT_HEADER = "실행 결과";
    private static final String CAR_RESULT_FORMAT_MESSAGE = "%s : %s\n";
    private static final String WINNER_FORMAT_MESSAGE = "우승자 : %s\n";

    public void printResult(GameResponseDto gameResponseDto) {
        System.out.println(RESULT_HEADER);
        System.out.printf(WINNER_FORMAT_MESSAGE, gameResponseDto.getWinners());
        gameResponseDto.getRacingCars()
                .forEach(this::getPrintf);
    }

    private void getPrintf(CarResponseDto car) {
        System.out.printf(CAR_RESULT_FORMAT_MESSAGE, car.getName(), car.getPosition());
    }

    public void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

}