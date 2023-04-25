package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;

public class OutputView {

    private final String ENTER_CAR_NAME = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private final String ENTER_TRY_TIMES = "시도할 횟수는 몇회인가요?";
    private final String RESULT = "실행 결과";
    private final String FINAL_WINNER = "%s(이)가 최종 우승했습니다.\n";
    private final String NAME_POSITION_DELIMITER = " : ";
    private final String WINNER_NAME_DELIMITER = ", ";

    public void printInputCarNamesNotice() {
        System.out.println(ENTER_CAR_NAME);
    }

    public void printInputTryTimesNotice() {
        System.out.println(ENTER_TRY_TIMES);
    }


    public void printResult(final ResultDto resultDto) {
        printResultNotice();
        printWinner(resultDto.getWinners());
        printAllStatus(resultDto.getCars());
    }

    private void printResultNotice() {
        System.out.println(RESULT);
    }

    private void printWinner(final List<CarDto> winners) {
        String winnerNames = winners.stream()
                .map(CarDto::getName)
                .collect(Collectors.joining(WINNER_NAME_DELIMITER));

        System.out.printf(FINAL_WINNER, winnerNames);
    }

    private void printAllStatus(final List<CarDto> cars) {
        for (CarDto car : cars) {
            System.out.println(car.getName() + NAME_POSITION_DELIMITER + car.getPosition());
        }
    }
}
