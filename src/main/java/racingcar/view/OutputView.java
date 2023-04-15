package racingcar.view;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.dto.RacingCarDto;

public class OutputView {
    private static final String CAR_NAME_REQUEST_MSG = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ROUND_COUNT_REQUEST_MSG = "시도할 회수는 몇회인가요?";
    private static final String POSITION_DELIMITER = "-";
    private static final String CAR_DELIMITER = "\n";
    private static final String RACING_RESULT_SUFFIX = "가 최종 우승했습니다.";

    private OutputView() {
    }

    public static void printCarNameRequestMsg() {
        System.out.println(CAR_NAME_REQUEST_MSG);
    }

    public static void printRoundCountRequestMsg() {
        System.out.println(ROUND_COUNT_REQUEST_MSG);
    }

    public static void printRoundState(List<RacingCarDto> racingCarDtos) {
        String racingState = racingCarDtos.stream()
                .map(OutputView::formatCarState)
                .collect(Collectors.joining(CAR_DELIMITER));
        System.out.println(racingState + CAR_DELIMITER);
    }

    private static String formatCarState(RacingCarDto racingCarDto) {
        return racingCarDto.getName() + " : " + POSITION_DELIMITER.repeat(racingCarDto.getPosition());
    }

    public static void printRacingResult(String winners) {
        System.out.println(winners + RACING_RESULT_SUFFIX);
    }

    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
