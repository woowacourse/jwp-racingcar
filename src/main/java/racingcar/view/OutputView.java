package racingcar.view;

import racingcar.dto.CarDto;
import racingcar.dto.RecordDto;

import java.util.stream.Collectors;

public class OutputView {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WIN_MENTION = "%s가 최종 우승했습니다.%n%n";
    public static final String CAR_RESULT = "이름: %s, 이동 거리 : %s\n";
    public static final String RECORD_NAME_FORMAT = "%-5s";

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
        return String.format(CAR_RESULT, padRight(car.getName()), car.getPosition());
    }

    public static String padRight(String s) {
        return String.format(RECORD_NAME_FORMAT, s);
    }
}
