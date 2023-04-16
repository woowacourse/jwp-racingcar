package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RecordDto;

import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WIN_MENTION = "%s가 최종 우승했습니다.%n%n";
    private static final String STICK = "-";
    public static final String CAR_RESULT = "%s : %s\n";

    public void result(RecordDto recordDto) {
        System.out.println(RESULT_HEADER);

        System.out.printf(WIN_MENTION, recordDto.getWinners());

        String result = recordDto.getRacingCars()
                .stream()
                .map(this::convertResultToString)
                .collect(Collectors.joining());
        System.out.println(result);
    }

    private String convertResultToString(CarDto car) {
        return String.format(CAR_RESULT, car.getName(), STICK.repeat(car.getPosition()));
    }
}
